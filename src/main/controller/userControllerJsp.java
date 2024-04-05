package main.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.model.Users;
import main.dao.UserDAO;


import java.io.IOException;

@WebServlet("/userControllerJsp")
public class userControllerJsp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public userControllerJsp() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mod = request.getParameter("mod");
        if(mod.equals("addUser")) {
            addUser(request, response);
        } else if (mod.equals("deleteUser")) {
            deleteUser(request, response);
        } else if (mod.equals("updateUser")) {
            updateUser(request, response);
        } else if (mod.equals("getInfo")) {
            getInfo(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    private void getInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
//      Set Id for new user
        Users user = new Users();
        user.setUserId(id);
//      Get user in db by ID
        UserDAO userDao = new UserDAO();
        Users u = userDao.selectById(user);
        String url = "";
        request.setAttribute("uUsername", u.getUsername());
        request.setAttribute("uPassword", u.getPassword());
        request.setAttribute("uRole", u.getRoleId());
        request.setAttribute("userId", u.getUserId());
        String mod = request.getParameter("mod");
        url = "/userUpdate.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("iUsername");
        String password = request.getParameter("iPassword");
        String role = request.getParameter("iRole");

        request.setAttribute("username", username);
        request.setAttribute("password", password);

        String url = "";
        String error = "";

        UserDAO userDAO = new UserDAO();
        if(userDAO.checkUsersname(username)) {
            error += "Đã tồn tại!";
        }

        request.setAttribute("error", error);
        if(error.length() > 0) {
            url = "/user_manage.jsp";
        }
        else {
            long time = System.currentTimeMillis();
            String id = "U" + String.valueOf(time).substring(0, 8);
            Users user = new Users(id, username, password, role);
            userDAO.insert(user);
            url = "/user_manage.jsp";
            request.setAttribute("username", "");
            request.setAttribute("password", "");
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String notification = "Thành công";
        String url = "";
        int isSuccess = 0;
        UserDAO userDAO = new UserDAO();
        Users u = new Users();
        u.setUserId(id);
        isSuccess = userDAO.delete(u);
        if(isSuccess == 0) {
            notification = "Không được phép xoá vì user này liên quan đến các thông tin khác.";
        }
        request.setAttribute("notification", notification);
        url = "/user_manage.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("uUsername");
        String password = request.getParameter("uPassword");
        String role = request.getParameter("uRole");
        String id = request.getParameter("userId");
        String error = "";
        String url = "";
        UserDAO userDAO = new UserDAO();

        if(userDAO.checkUsersnameDiferentId(username, id)) {
            error += "Đã tồn tại!";
            System.out.println("Đã tồn tại");
        }

        request.setAttribute("uError", error);
        if(error.length() > 0) {
            request.setAttribute("uUsername", username);
            request.setAttribute("uPassword", password);
            request.setAttribute("uRole", role);
            request.setAttribute("userId", id);
            url = "/userUpdate.jsp";
        } else {
            Users user = new Users(id, username, password, role);
            userDAO.update(user);
            url = "/user_manage.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
}