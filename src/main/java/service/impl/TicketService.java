package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.interfaces.ISeatDAO;
import dto.TicketDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;
import model.Ticket;
import model.TicketInformation;
import service.interfaces.ITicketService;
import utils.helper.IDGenerator;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TicketService implements ITicketService {
    @Inject
    private ISeatDAO iSeatDAO;

    @Override
    public Message CreateOne(TicketDto dto) {
        List<TicketInformation> information = dto.getSeats().stream().map(element -> {
            TicketInformation ticketInformation = new TicketInformation();
            ticketInformation.setCarriageId(element.getCarriageId());
            ticketInformation.setScheduleId(dto.getScheduleId());
            ticketInformation.setSeatNumber(element.getSeatNumber());
            return ticketInformation;
        }).toList();

        BigDecimal price = this.iSeatDAO.FindPrice(information);
        BigDecimal extraFee = price.multiply(BigDecimal.valueOf(0.15)).setScale(2, RoundingMode.UP);

        String orderId = IDGenerator.generate(10);
        Order order = new Order.Builder(orderId).WithUserId(IDGenerator.generate(10)).WithStatus("NAH").build();
        Ticket ticket = new Ticket.Builder(IDGenerator.generate(10)).WithOrderId(orderId).WithPrice(price).WithExtraFee(extraFee).WithPhoto(IDGenerator.generate(20)).WithUserId(dto.getUserId()).build();


        try {
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(ticket));
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(order));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(ticket).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
