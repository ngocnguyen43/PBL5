package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/loginJsp")
public class loginJsp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public loginJsp() {
        super();
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().append("Served at: ").append(request.getContextPath());
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        Users u = new Users();
//        u.setUsername(username);
//        u.setPassword(password);
//
//        UserDAO userDAO = new UserDAO();
//        Users user = userDAO.selectByUsernameAndPassword(u);
//        String url = "";
//        if(user != null) {
//            if (user.getRoleId().equals("1")) {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
//                url = "/welcomeDemo.jsp";
//            }
//        }
//        else {
//            request.setAttribute("error", "Tài khoản không tồn tại!");
//            url = "/login.jsp";
//        }
//        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
//        rd.forward(request, response);
//
//    }
}