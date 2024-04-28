package controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

@WebServlet(urlPatterns = {"/test1"})
public class TestController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = "Ha noi mua thu";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);
            BitMatrix t = new MultiFormatWriter().encode(data,BarcodeFormat.QR_CODE,200,200);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            MatrixToImageWriter.writeToStream(matrix,"PNG", bos);
            String image = Base64.getEncoder().encodeToString(bos.toByteArray());
            System.out.println(image);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }
}
