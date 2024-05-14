package service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dao.interfaces.ISeatDAO;
import dao.interfaces.ISeatsTicketsDAO;
import dao.interfaces.ITicketDAO;
import dto.QRDto;
import dto.TicketDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;
import model.Ticket;
import model.TicketInformation;
import service.interfaces.ITicketService;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.server.InternalServerException;
import utils.helper.IDGenerator;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Inet4Address;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketService implements ITicketService {
    @Inject
    private ISeatDAO iSeatDAO;
    @Inject
    private ISeatsTicketsDAO iSeatsTicketsDAO;
    @Inject
    private ITicketDAO iTicketDAO;

    @Override
    public Message BulkCreate(List<TicketDto> dto, String userId) throws BadRequestException, InternalServerException {
        List<TicketInformation> information = new ArrayList<>();
        List<String> ticketIds = dto.stream().map(e -> IDGenerator.generate(10)).toList();
        Set<Integer> seatNumber = new HashSet<>();
        AtomicInteger isValid = new AtomicInteger();
        AtomicInteger isBookedSeat = new AtomicInteger();
        try {
            for (var element : dto) {
                var index = dto.indexOf(element);
                var ticketId = ticketIds.get(index);
                var list = element.getSeats().stream().map(e -> {
                    TicketInformation ticketInformation = new TicketInformation();
                    ticketInformation.setCarriageId(e.getCarriageId());
                    ticketInformation.setScheduleId(element.getScheduleId());
                    ticketInformation.setSeatNumber(e.getSeatNumber());
                    ticketInformation.setTicketId(ticketId);
                    var seatsTickets = this.iSeatsTicketsDAO.FindAllConflicts(e.getCarriageId(), element.getScheduleId(), e.getSeatNumber());
                    System.out.println(seatsTickets.size());
                    if (!seatsTickets.isEmpty()) isBookedSeat.getAndIncrement();
                    if (seatNumber.contains(e.getSeatNumber())) {
                        isValid.getAndIncrement();
                    } else {
                        seatNumber.add(e.getSeatNumber());
                    }
                    return ticketInformation;
                }).toList();
                information.addAll(list);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerException();
        }
        if (isValid.get() > 0) {
            throw new BadRequestException("invalid properties");
        }
        if (isBookedSeat.get() > 0) throw new BadRequestException("Seats invalid");
        BigDecimal price = this.iSeatDAO.FindPrice(information);
        BigDecimal extraFee = price.multiply(BigDecimal.valueOf(0.15)).setScale(2, RoundingMode.UP);

        String orderId = IDGenerator.generate(10);

        Order order = new Order.Builder(orderId).WithUserId(userId).WithStatus("NAH").WithConfirmUrlId(IDGenerator.generate(30)).build();

        List<Ticket> tickets = dto.stream().map(e -> {
            var index = dto.indexOf(e);
            var ticketId = ticketIds.get(index);
            return new Ticket.Builder(ticketId).WithOrderId(orderId).WithPrice(price).WithExtraFee(extraFee).WithPhoto(IDGenerator.generate(20)).WithUserId(e.getUserId()).build();
        }).toList();


        try {
            String address = Inet4Address.getLocalHost().getHostAddress();
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix matrix = qrCodeWriter.encode("http://" + address + ":8080/api/v1/confirm/" + order.getConfirmUrlId(), BarcodeFormat.QR_CODE, 200, 200);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            MatrixToImageWriter.writeToStream(matrix, "PNG", bos);
            String image = Base64.getEncoder().encodeToString(bos.toByteArray());
            QRDto qrDto = new QRDto(image);
            this.iTicketDAO.BulkCreate(tickets, information, order);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            Data data = new Data.Builder(null).withResults(qrDto).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
}
