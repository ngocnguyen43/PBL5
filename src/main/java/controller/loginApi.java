//package org.example.ticketbox.Controller;
//
//import org.example.ticketbox.database.UserDAO;
//import org.json.simple.JSONObject;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet("/loginApi")
//public class loginApi extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    public loginApi() {
//        super();
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().append("Served at: ").append(request.getContextPath());
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String usn = request.getParameter("username");
//        String pwd = request.getParameter("password");
//        System.out.println(usn+"; "+pwd);
//
//        Users u = new Users();
//        u.setUsername(usn);
//        u.setPassword(pwd);
//
//        UserDAO userDAO = new UserDAO();
//        Users user = userDAO.selectByUsernameAndPassword(u);
//
//        JSONObject jsonResponse = new JSONObject();
//
////        System.out.println(user.getUsername()+"; "+user.getPassword());
//
//        if (user != null && user.getRoleId().equals("1")) {
//            HttpSession session = request.getSession();
//            session.setAttribute("user", user);
//            jsonResponse.put("status", "success");
//            jsonResponse.put("username", user.getUsername());
//            System.out.println("User User User User User User ");
//        } else {
//            System.out.println("Error Error Error Error Error ");
//            jsonResponse.put("status", "error");
//            jsonResponse.put("message", "Tai khoan khong ton tai!");
//        }
//
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        out.print(jsonResponse);
//        out.flush();
//    }
//}


package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/loginApi")
public class loginApi extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public loginApi() {
        super();
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().append("Served at: ").append(request.getContextPath());
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        StringBuilder sb = new StringBuilder();
//        BufferedReader reader = request.getReader();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            sb.append(line);
//        }
//
//        JSONParser parser = new JSONParser();
//        JSONObject requestData;
//        try {
//            requestData = (JSONObject) parser.parse(sb.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        String usn = (String) requestData.get("username");
//        String pwd = (String) requestData.get("password");
//        System.out.println(usn + "; " + pwd);
//
//        Users u = new Users();
//        u.setUsername(usn);
//        u.setPassword(pwd);
//
//        UserDAO userDAO = new UserDAO();
//        Users user = userDAO.selectByUsernameAndPassword(u);
//
//        JSONObject jsonResponse = new JSONObject();
//
//        if (user != null) {
//            if (user.getRoleId().equals("1")) {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
//                jsonResponse.put("status", "success");
//                jsonResponse.put("username", user.getUsername());
//                System.out.println("Admin - dang nhap thanh cong");
//            }
//            if (user.getRoleId().equals("2")) {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
//                jsonResponse.put("status", "success");
//                jsonResponse.put("username", user.getUsername());
//                System.out.println("Employees - dang nhap thanh cong");
//            }
//            if (user.getRoleId().equals("3")) {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
//                jsonResponse.put("status", "success");
//                jsonResponse.put("username", user.getUsername());
//                System.out.println("Customer - dang nhap thanh cong");
//            }
//            if (user.getRoleId().equals("4")) {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
//                jsonResponse.put("status", "success");
//                jsonResponse.put("username", user.getUsername());
//                System.out.println("Provider - dang nhap thanh cong");
//            }
//        }
//        else {
//            System.out.println("Error Error Error Error Error ");
//            jsonResponse.put("status", "error");
//            jsonResponse.put("message", "Tai khoan khong ton tai!");
//        }
//
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        out.print(jsonResponse);
//        out.flush();
//    }
}
