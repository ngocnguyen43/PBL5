package org.example.ticketbox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginDemo")
public class loginDemo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Thực hiện kiểm tra tên người dùng và mật khẩu (ở đây chỉ là kiểm tra mẫu)
        if ("admin".equals(username) && "admin".equals(password)) {
            request.getSession().setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/welcomeDemo.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/loginDemo.jsp?error=true");
        }
    }
}
