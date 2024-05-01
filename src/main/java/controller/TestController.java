package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

@WebServlet(urlPatterns = {"/test/1"})
public class TestController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        User user = (User) req.getAttribute("user");
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user));

    }
}
