package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.websocket.WebSocket;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.IRevenueService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/api/v1/test/1"})
public class TestController extends HttpServlet {
    @Inject
    private IRevenueService iRevenueService;
    @Inject
    private WebSocket webSocket;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        webSocket.emitMessage("123", "alo");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String data = "Ha noi mua thu";
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        try {
//            BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);
//            BitMatrix t = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 200, 200);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//            MatrixToImageWriter.writeToStream(matrix, "PNG", bos);
//            String image = Base64.getEncoder().encodeToString(bos.toByteArray());
//            System.out.println(image);
//        } catch (WriterException e) {
//            throw new RuntimeException(e);
//        }
//        Cookie uiColorCookie = new Cookie("color", "red");
//        resp.addCookie(uiColorCookie);
//        System.out.println(req.getAttribute("test"));
//        User user = (User) req.getAttribute("user");
//        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user));

//        TokenDto[] dtos = Helper.arrayParamsToString(req.getParameterMap()).stream().map(element->{
//            TokenDto dto = new TokenDto();
//            dto.setRefreshToken(element.);
//        })
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        // Parse the JSON data into a list of objects
        ObjectMapper objectMapper = new ObjectMapper();
        MyObject myObjects = objectMapper.readValue(sb.toString(), MyObject.class);
//
//        MyObject myObjects =Helper.paramsToString(req.getParameterMap()).toModel(MyObject.class);
//        // Process the data
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(myObjects));
    }

    static class MyObject {
        public List<String> name;

        public List<String> getName() {
            return name;
        }

        public void setName(List<String> name) {
            this.name = name;
        }
    }
}
