package org.example.ticketbox.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ticketbox.Model.Employees;
import org.example.ticketbox.Model.Users;
import org.example.ticketbox.database.EmployeesDAO;
import org.example.ticketbox.database.UserDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/employeeController")
public class employeeControllerApi extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public employeeControllerApi() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mod = request.getParameter("mod");
        if (mod != null) {
            switch (mod) {
                case "selectAllEmployee":
                    selectAllEmployee(request, response);
                    break;
                case "getInfoEmployee":
                    getInfoEmployee(request, response);
                    break;
                case "addEmployee":
                    addEmployee(request, response);
                    break;
                case "deleteEmployee":
                    deleteEmployee(request, response);
                    break;
                case "updateEmployee":
                    updateEmployee(request, response);
                    break;
                default:
                    sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
                    break;
            }
        } else {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    // API methods

    private void selectAllEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
////    http://localhost:8080/ticketbox_war/userController?mod=selectAllEmployee
        EmployeesDAO employeeDAO = new EmployeesDAO();
        ArrayList<Employees> employeeList = employeeDAO.selectAll();
        try {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");

            JSONArray employeesArray = new JSONArray();
            for (Employees employee : employeeList) {
                JSONObject employeeObj = new JSONObject();
                employeeObj.put("full_name", employee.getFullName());
                employeeObj.put("gender", employee.getGender());
                employeeObj.put("email", employee.getEmail());
                employeeObj.put("phoneNumber", employee.getPhoneNumber());
                employeeObj.put("position", employee.getPosition());
                employeeObj.put("date_of_birth", employee.getDateOfBirth());
                employeeObj.put("photo", employee.getPhoto());
                employeesArray.add(employeeObj);
            }

            jsonResponse.put("employee", employeesArray);

            sendJsonResponse(response, jsonResponse);
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while creating JSON response");
        }
    }

    private void getInfoEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
////    http://localhost:8080/ticketbox_war/employeeController?mod=getInfoEmployee&employeeId=10002
        String employeeId = request.getParameter("employeeId");

        if (employeeId == null || employeeId.isEmpty()) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Employee ID is required");
            return;
        }

        EmployeesDAO employeeDAO = new EmployeesDAO();
        Employees employee = employeeDAO.selectById(employeeId);

        if (employee == null) {
            sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "Employee not found");
            return;
        }

        try {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");

            JSONArray employeesArray = new JSONArray();
            JSONObject employeeObj = new JSONObject();
            employeeObj.put("fullName", employee.getFullName());
            employeeObj.put("gender", employee.getGender());
            employeeObj.put("email", employee.getEmail());
            employeeObj.put("phoneNumber", employee.getPhoneNumber());
            employeeObj.put("position", employee.getPosition());
            employeeObj.put("date_of_birth", employee.getDateOfBirth());
            employeeObj.put("photo", employee.getPhoto());
            employeesArray.add(employeeObj);

            jsonResponse.put("employee", employeesArray);

            sendJsonResponse(response, jsonResponse);
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while creating JSON response");
        }
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        http://localhost:8080/ticketbox_war/employeeController?mod=addEmployee
//        {
//            "username": "employee02",
//            "password": "employee02",
//            "fullName": "employee02",
//            "gender": "nữ",
//            "email": "employee02@gmail.com",
//            "phoneNumber": "0990200391",
//            "position": "Đà nẵng",
//            "date_of_birth": "2003-09-14",
//            "photo": "null",
//            "roleId": "2"
//        }
        try {
            // Lấy dữ liệu từ request
            String requestData = getRequestBody(request);
            JSONObject requestDataJson = (JSONObject) new JSONParser().parse(requestData);

            // Trích xuất thông tin từ dữ liệu JSON
            String username = (String) requestDataJson.get("username");
            String password = (String) requestDataJson.get("password");
            String fullName = (String) requestDataJson.get("fullName");
            String gender = (String) requestDataJson.get("gender");
            String email = (String) requestDataJson.get("email");
            String phoneNumber = (String) requestDataJson.get("phoneNumber");
            String position = (String) requestDataJson.get("position");
            String dateOfBirthStr = (String) requestDataJson.get("date_of_birth");
            String photo = (String) requestDataJson.get("photo");
            String roleId = (String) requestDataJson.get("roleId");

            // Xử lý ngày sinh
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date_of_birth_t = null;
            try {
                date_of_birth_t = dateFormat.parse(dateOfBirthStr);
            } catch (java.text.ParseException e) {
                throw new RuntimeException(e);
            }
            java.sql.Date dateOfBirth = new java.sql.Date(date_of_birth_t.getTime());

            long time = System.currentTimeMillis();
            String u_id = "U" + String.valueOf(time).substring(0, 9);
            String e_id = "E" + String.valueOf(time).substring(0, 9);
            System.out.println(u_id + "; " + e_id + "; " + username + "; " + password + "; " + fullName);

            UserDAO userDAO = new UserDAO();
            Users user = new Users(u_id, username, password, roleId);
            userDAO.insert(user);

            // Tạo thông tin nhân viên mới và thêm vào CSDL
            Employees newEmployee = new Employees(e_id, u_id, fullName, gender, email, phoneNumber, position, dateOfBirth, photo);
            EmployeesDAO employeeDAO = new EmployeesDAO();
            employeeDAO.insert(newEmployee);

            // Gửi phản hồi JSON thành công
            sendSuccessResponse(response, "Employee added successfully");
        } catch (ParseException e) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while adding employee");
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    http://localhost:8080/ticketbox_war/userController?mod=deleteEmployee&employeeId=10003
        try {
            // Lấy employeeId từ URL
            String employeeId = request.getParameter("employeeId");

            if (employeeId == null || employeeId.isEmpty()) {
                sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Employee ID is required");
                return;
            }

            // Xóa nhân viên
            EmployeesDAO employeeDAO = new EmployeesDAO();
            Employees employees = employeeDAO.selectById(employeeId);

            UserDAO userDAO = new UserDAO();
            Users users = userDAO.selectById(employees.getUserId());

            employeeDAO.delete(employees);
            userDAO.delete(users);

            // Gửi phản hồi JSON thành công
            sendSuccessResponse(response, "Employee deleted successfully");
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while deleting employee");
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        http://localhost:8080/ticketbox_war/employeeController?mod=updateEmployee//
//        {
//            "fullName": "Nguyễn Phạm Nhật Vỹ",
//            "gender": "nữ",
//            "email": "nhatvy10@gmail.com",
//            "phoneNumber": "0900920002",
//            "position": "Hòa Quý - Đà nẵng",
//            "date_of_birth": "2003-11-21",
//            "photo": "null",
//            "roleId": "2",
//            "employeeId": "E17107332"
//        }
            try {
            // Lấy dữ liệu từ request
            String requestData = getRequestBody(request);
            JSONObject requestDataJson = (JSONObject) new JSONParser().parse(requestData);

            // Trích xuất thông tin từ dữ liệu JSON
            String employeeId = (String) requestDataJson.get("employeeId");
            String fullName = (String) requestDataJson.get("fullName");
            String gender = (String) requestDataJson.get("gender");
            String email = (String) requestDataJson.get("email");
            String phoneNumber = (String) requestDataJson.get("phoneNumber");
            String position = (String) requestDataJson.get("position");
            String dateOfBirthStr = (String) requestDataJson.get("date_of_birth");
            String photo = (String) requestDataJson.get("photo");

            // Xử lý ngày sinh
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date_of_birth_t = null;
            try {
                date_of_birth_t = dateFormat.parse(dateOfBirthStr);
            } catch (java.text.ParseException e) {
                throw new RuntimeException(e);
            }
            java.sql.Date dateOfBirth = new java.sql.Date(date_of_birth_t.getTime());

                // Tạo đối tượng nhân viên và cập nhật vào CSDL
            Employees updatedEmployee = new Employees();
            updatedEmployee.setEmployeeId(employeeId);
            updatedEmployee.setFullName(fullName);
            updatedEmployee.setGender(gender);
            updatedEmployee.setEmail(email);
            updatedEmployee.setPhoneNumber(phoneNumber);
            updatedEmployee.setPosition(position);
            updatedEmployee.setDateOfBirth(dateOfBirth);
            updatedEmployee.setPhoto(photo);

            EmployeesDAO employeeDAO = new EmployeesDAO();
            employeeDAO.update(updatedEmployee);

            // Gửi phản hồi JSON thành công
            sendSuccessResponse(response, "Employee updated successfully");
        } catch (ParseException e) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while updating employee");
        }
    }

    // Phương thức gửi phản hồi thành công
    private void sendSuccessResponse(HttpServletResponse response, String message) throws IOException {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "success");
        jsonResponse.put("message", message);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(jsonResponse.toJSONString());
    }

    // Phương thức gửi phản hồi lỗi
    private void sendErrorResponse(HttpServletResponse response, int statusCode, String errorMessage) throws IOException {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "error");
        jsonResponse.put("message", errorMessage);

        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(jsonResponse.toJSONString());
    }

    // Phương thức lấy nội dung của request body
    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    // Phương thức để gửi phản hồi JSON
    private void sendJsonResponse(HttpServletResponse response, JSONObject jsonResponse) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(jsonResponse.toString());
    }
}


//package org.example.ticketbox.Controller;
//
//import org.example.ticketbox.Model.Employees;
//import org.example.ticketbox.Model.Users;
//import org.example.ticketbox.database.EmployeesDAO;
//import org.example.ticketbox.database.UserDAO;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//@WebServlet("/employeeController")
//public class employeeControllerApi extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    public employeeControllerApi() {
//        super();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String mod = request.getParameter("mod");
//        if (mod != null) {
//            switch (mod) {
//                case "selectAllEmployee":
//                    selectAllEmployee(request, response);
//                    break;
//                case "getInfoEmployee":
//                    getInfoEmployee(request, response);
//                    break;
//                case "addEmployee":
//                    addEmployee(request, response);
//                    break;
//                case "deleteEmployee":
//                    deleteEmployee(request, response);
//                    break;
//                case "updateEmployee":
//                    updateEmployee(request, response);
//                    break;
//                default:
//                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                    response.getWriter().println("Invalid request");
//                    break;
//            }
//        } else {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().println("Invalid request");
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        doGet(request, response);
//    }
//
//    // Định nghĩa các phương thức API ở đây...
//    private void selectAllEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
////    http://localhost:8080/ticketbox_war/userController?mod=selectAllEmployee
//        EmployeesDAO employeeDAO = new EmployeesDAO();
//        ArrayList<Employees> employeeList = employeeDAO.selectAll();
//        try {
//            JSONObject jsonResponse = new JSONObject();
//            jsonResponse.put("status", "success");
//
//            JSONArray employeesArray = new JSONArray();
//            for (Employees employee : employeeList) {
//                JSONObject employeeObj = new JSONObject();
//                employeeObj.put("full_name", employee.getFullName());
//                employeeObj.put("gender", employee.getGender());
//                employeeObj.put("email", employee.getEmail());
//                employeeObj.put("phoneNumber", employee.getPhoneNumber());
//                employeeObj.put("position", employee.getPosition());
//                employeeObj.put("date_of_birth", employee.getDateOfBirth());
//                employeeObj.put("photo", employee.getPhoto());
//                employeesArray.add(employeeObj);
//            }
//
//            jsonResponse.put("employee", employeesArray);
//
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(jsonResponse.toString());
//        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().println("Error while creating JSON response");
//        }
//    }
//
//    private void getInfoEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
////    http://localhost:8080/ticketbox_war/employeeController?mod=getInfoEmployee&employeeId=10002
//        // Lấy employee_id từ URL
//        String employeeId = request.getParameter("employeeId");
//        System.out.println("Employee ID: " + employeeId);
//
//        if (employeeId == null || employeeId.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().println("User ID is required");
//            return;
//        }
//
//        String error = "";
//        String url = "";
//
//        JSONObject jsonResponse = new JSONObject();
//
//        EmployeesDAO employeeDAO = new EmployeesDAO();
//        Employees employee = employeeDAO.selectById(employeeId);
//
//        if (employee == null) {
//            error += "Khong ton tai employee co id="+employeeId+"!!!";
//            jsonResponse.put("status", "error");
//            jsonResponse.put("value", error);
//            System.out.println("khong ton tai employee co id="+employeeId+"!!!");
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(jsonResponse.toString());
//            return;
//        }
//
//        try {
//            jsonResponse.put("status", "success");
//
//            JSONArray employeesArray = new JSONArray();
//            JSONObject employeeObj = new JSONObject();
//            employeeObj.put("fullName", employee.getFullName());
//            employeeObj.put("gender", employee.getGender());
//            employeeObj.put("email", employee.getEmail());
//            employeeObj.put("phoneNumber", employee.getPhoneNumber());
//            employeeObj.put("position", employee.getPosition());
//            employeeObj.put("date_of_birth", employee.getDateOfBirth());
//            employeeObj.put("photo", employee.getPhoto());
//            employeesArray.add(employeeObj);
//
//            jsonResponse.put("employee", employeesArray);
//
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(jsonResponse.toString());
//        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().println("Error while creating JSON response");
//        }
//    }
//
//    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        http://localhost:8080/ticketbox_war/employeeController?mod=addEmployee
////        {
////            "username": "employee02",
////                "password": "employee02",
////                "fullName": "employee02",
////                "gender": "nữ",
////                "email": "employee02@gmail.com",
////                "phoneNumber": "0990200391",
////                "position": "Đà nẵng",
////                "date_of_birth": "2003-09-14",
////                "photo": "null",
////                "roleId": "3"
////        }
//
//        // Thêm người dùng mới và trả về kết quả dưới dạng JSON
//        request.setCharacterEncoding("UTF-8");
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
//        String username = (String) requestData.get("username");
//        String password = (String) requestData.get("password");
//        String fullName = (String) requestData.get("fullName");
//        String gender = (String) requestData.get("gender");
//        String email = (String) requestData.get("email");
//        String phoneNumber = (String) requestData.get("phoneNumber");
//        String position = (String) requestData.get("position");
//        String date_of_birth_tmp = (String) requestData.get("date_of_birth");
//        String photo = (String) requestData.get("photo");
//        String role = (String) requestData.get("roleId");
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date_of_birth_t = null;
//        try {
//            date_of_birth_t = dateFormat.parse(date_of_birth_tmp);
//        } catch (java.text.ParseException e) {
//            throw new RuntimeException(e);
//        }
//        java.sql.Date date_of_birth = new java.sql.Date(date_of_birth_t.getTime());
//
//        long time = System.currentTimeMillis();
//        String u_id = "U" + String.valueOf(time).substring(0, 8);
//        String e_id = "E" + String.valueOf(time).substring(0, 8);
//        System.out.println(u_id + "; " + e_id + "; " + username + "; " + password + "; " + fullName);
//
//        String error = "";
//        String url = "";
//        UserDAO userDAO = new UserDAO();
//        EmployeesDAO employeesDAO = new EmployeesDAO();
//
//        JSONObject jsonResponse = new JSONObject();
//        if (userDAO.checkUsersnameDiferentId(username, u_id)) {
//            error += "Đã tồn tại!";
//            jsonResponse.put("status", "error");
//            jsonResponse.put("value", error);
//            System.out.println("Đã tồn tại");
//        }
//
//        request.setAttribute("uError", error);
//        if (error.length() > 0) {
//            request.setAttribute("uUsername", username);
//            request.setAttribute("uPassword", password);
//            request.setAttribute("uRole", role);
//            request.setAttribute("userId", u_id);
//
//            jsonResponse.put("status", "error");
//            jsonResponse.put("value", "addUser khong thanh cong");
//            System.out.println("addUser không thành công");
////            url = "/userAdd.jsp";
//        } else {
//            Users user = new Users(u_id, username, password, role);
//            userDAO.insert(user);
//            System.out.println("addUser thành công");
//
//            Employees employee = new Employees(e_id, u_id, fullName, gender, email, phoneNumber, position, date_of_birth, photo);
//            employeesDAO.insert(employee);
//
//            jsonResponse.put("status", "success");
//            jsonResponse.put("value", "Add Employee thành công");
//            System.out.println("Add Employee thành công");
////            url = "/user_manage.jsp";
//        }
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        out.print(jsonResponse);
//        out.flush();
//    }
//
//    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        // Xóa người dùng và trả về kết quả dưới dạng JSON
//        //    http://localhost:8080/ticketbox_war/userController?mod=deleteEmployee&employeeId=10003
//        // Lấy user_id từ URL
//        String employeeId = request.getParameter("employeeId");
//        System.out.println("Employee ID: " + employeeId);
//
//        if (employeeId == null || employeeId.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().println("User ID is required");
//            return;
//        }
//
//        String error = "";
//        String url = "";
//
//        JSONObject jsonResponse = new JSONObject();
//
//        EmployeesDAO employeesDAO = new EmployeesDAO();
//        Employees employee = new Employees();
//        employee = employeesDAO.selectById(employeeId);
//
//        UserDAO userDAO = new UserDAO();
//        Users user = new Users();
//        user.setUserId(employee.getUserId());
//
//        int employeeResult = employeesDAO.delete(employee);
//        if (employeeResult == 0) {
//            error += "Không được phép xoá vì employee này liên quan đến các thông tin khác.";
//            jsonResponse.put("status", "error");
//            jsonResponse.put("message", error);
//            System.out.println("Không được phép xoá vì employee này liên quan đến các thông tin khác.");
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(jsonResponse.toString());
//            return;
//        }
//
//        int userResult = userDAO.delete(user);
//        if (userResult == 0) {
//            error += "Không được phép xoá vì user này liên quan đến các thông tin khác.";
//            jsonResponse.put("status", "error");
//            jsonResponse.put("message", error);
//            System.out.println("Không được phép xoá vì user này liên quan đến các thông tin khác.");
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(jsonResponse.toString());
//            return;
//        }
//
//        try {
//            jsonResponse.put("status", "success");
//            jsonResponse.put("value", "Xoa employee thanh cong");
//
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(jsonResponse.toString());
//        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().println("Error while creating JSON response");
//        }
//    }
//
//    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        http://localhost:8080/ticketbox_war/employeeController?mod=updateEmployee//        {
////            "fullName": "Nguyễn Phạm Nhật Vỹ",
////            "gender": "nữ",
////            "email": "nhatvy10@gmail.com",
////            "phoneNumber": "0900920002",
////            "position": "Hòa Quý - Đà nẵng",
////            "date_of_birth": "2003-11-21",
////            "photo": "null",
////            "roleId": "2",
////            "employeeId": "E17107332"
////        }
//        // Cập nhật thông tin người dùng và trả về kết quả dưới dạng JSON
//        request.setCharacterEncoding("UTF-8");
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
//        String fullname = (String) requestData.get("fullName");
//        String gender = (String) requestData.get("gender");
//        String email = (String) requestData.get("email");
//        String phonenumber = (String) requestData.get("phoneNumber");
//        String position = (String) requestData.get("position");
//        String date_of_birth_tmp = (String) requestData.get("date_of_birth");
//        String photo = (String) requestData.get("photo");
//        String employeeId = (String) requestData.get("employeeId");
//        System.out.println(employeeId + "; " + fullname + "; " + gender + "; " + email + "; " + phonenumber + "; " + position + "; " + date_of_birth_tmp);
//
////      Format Date SQL
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date_of_birth_t = null;
//        try {
//            date_of_birth_t = dateFormat.parse(date_of_birth_tmp);
//        } catch (java.text.ParseException e) {
//            throw new RuntimeException(e);
//        }
//        java.sql.Date date_of_birth = new java.sql.Date(date_of_birth_t.getTime());
//
//        String error = "";
////        String url = "";
//        EmployeesDAO employeesDAO = new EmployeesDAO();
//        JSONObject jsonResponse = new JSONObject();
//
//        Employees employees_old = employeesDAO.selectById(employeeId);
//        Employees employees = new Employees(employeeId, employees_old.getUserId(), fullname, gender, email, phonenumber, position, date_of_birth, photo);
//        employeesDAO.update(employees);
//
//        jsonResponse.put("status", "success");
//        System.out.println("Update User thành công");
////      url = "/user_manage.jsp";
//
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        out.print(jsonResponse);
//        out.flush();
//    }
//}
