package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/customerController")
public class customerControllerApi extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public customerControllerApi() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String mod = request.getParameter("mod");
//        if (mod != null) {
//            switch (mod) {
//                case "selectAllCustomer":
//                    selectAllCustomer(request, response);
//                    break;
//                case "getInfoCustomer":
//                    getInfoCustomer(request, response);
//                    break;
//                case "addCustomer":
//                    addCustomer(request, response);
//                    break;
//                case "deleteCustomer":
//                    deleteCustomer(request, response);
//                    break;
//                case "updateCustomer":
//                    updateCustomer(request, response);
//                    break;
//                default:
//                    sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
//                    break;
//            }
//        } else {
//            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    // API methods

    private void selectAllCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
////    http://localhost:8080/ticketbox_war/customerController?mod=selectAllCustomer
//        CustomerDAO customerDAO = new CustomerDAO();
//        ArrayList<Customer> customerList = customerDAO.selectAll();
//        try {
//            JSONObject jsonResponse = new JSONObject();
//            jsonResponse.put("status", "success");
//
//            JSONArray customersArray = new JSONArray();
//            for (Customer customer : customerList) {
//                JSONObject customerObj = new JSONObject();
//                customerObj.put("full_name", customer.getFullName());
//                customerObj.put("email", customer.getEmail());
//                customerObj.put("phoneNumber", customer.getPhoneNumber());
//                customerObj.put("position", customer.getPosition());
//                customerObj.put("photo", customer.getPhoto());
//                customersArray.add(customerObj);
//            }
//
//            jsonResponse.put("customer", customersArray);
//
//            sendJsonResponse(response, jsonResponse);
//        } catch (Exception e) {
//            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while creating JSON response");
//        }
    }

    private void getInfoCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
////    http://localhost:8080/ticketbox_war/customerController?mod=getInfoCustomer&customerId=10002
        String customerId = request.getParameter("customerId");

//        if (customerId == null || customerId.isEmpty()) {
//            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "customer ID is required");
//            return;
//        }
//
//        CustomerDAO customerDAO = new CustomerDAO();
//        Customer customer = customerDAO.selectById(customerId);
//
//        if (customer == null) {
//            sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "customer not found");
//            return;
//        }
//
//        try {
//            JSONObject jsonResponse = new JSONObject();
//            jsonResponse.put("status", "success");
//
//            JSONArray customersArray = new JSONArray();
//            JSONObject customerObj = new JSONObject();
//            customerObj.put("fullName", customer.getFullName());
//            customerObj.put("email", customer.getEmail());
//            customerObj.put("phoneNumber", customer.getPhoneNumber());
//            customerObj.put("position", customer.getPosition());
//            customerObj.put("photo", customer.getPhoto());
//            customersArray.add(customerObj);
//
//            jsonResponse.put("customer", customersArray);
//
//            sendJsonResponse(response, jsonResponse);
//        } catch (Exception e) {
//            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while creating JSON response");
//        }
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        http://localhost:8080/ticketbox_war/customerController?mod=addCustomer
//        {
//            "username": "customer02",
//            "password": "customer02",
//            "fullName": "customer02",
//            "email": "customer02@gmail.com",
//            "phoneNumber": "0990200391",
//            "position": "Đà nẵng",
//            "photo": "null",
//            "roleId": "3"
//        }
//        try {
//            // Lấy dữ liệu từ request
//            String requestData = getRequestBody(request);
//            JSONObject requestDataJson = (JSONObject) new JSONParser().parse(requestData);
//
//            // Trích xuất thông tin từ dữ liệu JSON
//            String username = (String) requestDataJson.get("username");
//            String password = (String) requestDataJson.get("password");
//            String fullName = (String) requestDataJson.get("fullName");
//            String email = (String) requestDataJson.get("email");
//            String phoneNumber = (String) requestDataJson.get("phoneNumber");
//            String position = (String) requestDataJson.get("position");
//            String photo = (String) requestDataJson.get("photo");
//            String roleId = (String) requestDataJson.get("roleId");
//
//            long time = System.currentTimeMillis();
//            String u_id = "U" + String.valueOf(time).substring(0, 9);
//            String c_id = "C" + String.valueOf(time).substring(0, 9);
//            System.out.println(u_id + "; " + c_id + "; " + username + "; " + password + "; " + fullName);
//
//            UserDAO userDAO = new UserDAO();
//            Users user = new Users(u_id, username, password, roleId);
//            userDAO.insert(user);
//
//            // Tạo thông tin nhân viên mới và thêm vào CSDL
//            Customer newCustomer = new Customer(c_id, u_id, fullName, email, phoneNumber, position, photo);
//            CustomerDAO customerDAO = new CustomerDAO();
//            customerDAO.insert(newCustomer);
//
//            // Gửi phản hồi JSON thành công
//            sendSuccessResponse(response, "customer added successfully");
//        } catch (ParseException e) {
//            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
//        } catch (Exception e) {
//            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while adding customer");
//        }
    }

//    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //    http://localhost:8080/ticketbox_war/customerController?mod=deleteCustomer&customerId=10003
//        try {
//            // Lấy customerId từ URL
//            String customerId = request.getParameter("customerId");
//
//            if (customerId == null || customerId.isEmpty()) {
//                sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "customer ID is required");
//                return;
//            }
//
//            // Xóa nhân viên
//            CustomerDAO customerDAO = new CustomerDAO();
//            Customer customer = customerDAO.selectById(customerId);
//
//            UserDAO userDAO = new UserDAO();
//            Users users = userDAO.selectById(customer.getUserId());
//
//            customerDAO.delete(customer);
//            userDAO.delete(users);
//
//            // Gửi phản hồi JSON thành công
//            sendSuccessResponse(response, "customer deleted successfully");
//        } catch (Exception e) {
//            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while deleting customer");
//        }
    }

//    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        http://localhost:8080/ticketbox_war/customerController?mod=updateCustomer//
////        {
////            "fullName": "Nguyễn Phạm Nhật Vỹ",
////            "email": "nhatvy10@gmail.com",
////            "phoneNumber": "0900920002",
////            "position": "Hòa Quý - Đà nẵng",
////            "photo": "null",
////            "roleId": "2",
////            "customerId": "E17107332"
////        }
//        try {
//            // Lấy dữ liệu từ request
//            String requestData = getRequestBody(request);
//            JSONObject requestDataJson = (JSONObject) new JSONParser().parse(requestData);
//
//            // Trích xuất thông tin từ dữ liệu JSON
//            String customerId = (String) requestDataJson.get("customerId");
//            String fullName = (String) requestDataJson.get("fullName");
//            String email = (String) requestDataJson.get("email");
//            String phoneNumber = (String) requestDataJson.get("phoneNumber");
//            String position = (String) requestDataJson.get("position");
//            String photo = (String) requestDataJson.get("photo");
//
//            // Tạo đối tượng nhân viên và cập nhật vào CSDL
//            Customer updatedCustomer = new Customer();
//            updatedCustomer.setCustomerId(customerId);
//            updatedCustomer.setFullName(fullName);
//            updatedCustomer.setEmail(email);
//            updatedCustomer.setPhoneNumber(phoneNumber);
//            updatedCustomer.setPosition(position);
//            updatedCustomer.setPhoto(photo);
//
//            CustomerDAO customerDAO = new CustomerDAO();
//            int result = customerDAO.update(updatedCustomer);
//
//            if(result!=0)
//                // Gửi phản hồi JSON thành công
//                sendSuccessResponse(response, "customer updated successfully");
//            else
//                sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while updating customer");
//        } catch (ParseException e) {
//            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
//        } catch (Exception e) {
//            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while updating customer");
//        }
//    }

    // Phương thức gửi phản hồi thành công
//    private void sendSuccessResponse(HttpServletResponse response, String message) throws IOException {
//        JSONObject jsonResponse = new JSONObject();
//        jsonResponse.put("status", "success");
//        jsonResponse.put("message", message);
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().println(jsonResponse.toJSONString());
//    }
//
//    // Phương thức gửi phản hồi lỗi
//    private void sendErrorResponse(HttpServletResponse response, int statusCode, String errorMessage) throws IOException {
//        JSONObject jsonResponse = new JSONObject();
//        jsonResponse.put("status", "error");
//        jsonResponse.put("message", errorMessage);
//
//        response.setStatus(statusCode);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().println(jsonResponse.toJSONString());
//    }
//
//    // Phương thức lấy nội dung của request body
//    private String getRequestBody(HttpServletRequest request) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        BufferedReader reader = request.getReader();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            sb.append(line);
//        }
//        return sb.toString();
//    }
//
//    // Phương thức để gửi phản hồi JSON
//    private void sendJsonResponse(HttpServletResponse response, JSONObject jsonResponse) throws IOException {
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().println(jsonResponse.toString());
//    }



//// Lấy ID = thời gian hiện tại tính bằng mili giây
//long time = System.currentTimeMillis();
//String timeString = String.valueOf(time);
//int length = timeString.length();
//String u_id = "U" + timeString.substring(length - 9); // Lấy 9 ký tự cuối cùng để tạo u_id
//String e_id = "E" + timeString.substring(length - 9); // Lấy 9 ký tự cuối cùng để tạo c_id
//            System.out.println(u_id + "; " + e_id + "; " + username + "; " + password + "; " + fullName);
