package org.example.ticketbox.Controller;

import org.example.ticketbox.Model.TicketProviders;
import org.example.ticketbox.Model.Users;
import org.example.ticketbox.database.TicketProvidersDAO;
import org.example.ticketbox.database.UserDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ticketProviderController")
public class ticketProviderControllerApi extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ticketProviderControllerApi() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mod = request.getParameter("mod");
        if (mod != null) {
            switch (mod) {
                case "selectAllTicketProvider":
                    selectAllTicketProvider(request, response);
                    break;
                case "getInfoTicketProvider":
                    getInfoTicketProvider(request, response);
                    break;
                case "addTicketProvider":
                    addTicketProvider(request, response);
                    break;
                case "deleteTicketProvider":
                    deleteTicketProvider(request, response);
                    break;
                case "updateTicketProvider":
                    updateTicketProvider(request, response);
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

    private void selectAllTicketProvider(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ////    http://localhost:8080/ticketbox_war/ticketProviderController?mod=selectAllticketProvider
        TicketProvidersDAO ticketProviderDAO = new TicketProvidersDAO();
        ArrayList<TicketProviders> ticketProviderList = ticketProviderDAO.selectAll();
        try {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");

            JSONArray ticketProvidersArray = new JSONArray();
            for (TicketProviders ticketProvider : ticketProviderList) {
                JSONObject ticketProviderObj = new JSONObject();
                ticketProviderObj.put("provider_id", ticketProvider.getProvidersId());
                ticketProviderObj.put("user_id", ticketProvider.getUserId());
                ticketProviderObj.put("full_name", ticketProvider.getFullName());
                ticketProviderObj.put("email", ticketProvider.getEmail());
                ticketProviderObj.put("phoneNumber", ticketProvider.getPhoneNumber());
                ticketProviderObj.put("position", ticketProvider.getPosition());
                ticketProviderObj.put("provider_name", ticketProvider.getProviderName());
                ticketProviderObj.put("contact_info", ticketProvider.getContactInfo());
                ticketProviderObj.put("is_confirmed", ticketProvider.isConfirmed());
                ticketProviderObj.put("photo", ticketProvider.getPhoto());
                ticketProvidersArray.add(ticketProviderObj);
            }

            jsonResponse.put("ticketProvider", ticketProvidersArray);

            sendJsonResponse(response, jsonResponse);
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while creating JSON response");
        }
    }

    private void getInfoTicketProvider(HttpServletRequest request, HttpServletResponse response) throws IOException {
////    http://localhost:8080/ticketbox_war/ticketProviderController?mod=getInfoticketProvider&ticketProviderId=10002
        String ticketProviderId = request.getParameter("ticketProviderId");

        if (ticketProviderId == null || ticketProviderId.isEmpty()) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "ticketProvider ID is required");
            return;
        }

        TicketProvidersDAO ticketProviderDAO = new TicketProvidersDAO();
        TicketProviders ticketProvider = ticketProviderDAO.selectById(ticketProviderId);

        if (ticketProvider == null) {
            sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "ticketProvider not found");
            return;
        }

        try {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");

            JSONArray ticketProvidersArray = new JSONArray();
            JSONObject ticketProviderObj = new JSONObject();
            ticketProviderObj.put("provider_id", ticketProvider.getProvidersId());
            ticketProviderObj.put("user_id", ticketProvider.getUserId());
            ticketProviderObj.put("full_name", ticketProvider.getFullName());
            ticketProviderObj.put("email", ticketProvider.getEmail());
            ticketProviderObj.put("phoneNumber", ticketProvider.getPhoneNumber());
            ticketProviderObj.put("position", ticketProvider.getPosition());
            ticketProviderObj.put("provider_name", ticketProvider.getProviderName());
            ticketProviderObj.put("contact_info", ticketProvider.getContactInfo());
            ticketProviderObj.put("is_confirmed", ticketProvider.isConfirmed());
            ticketProviderObj.put("photo", ticketProvider.getPhoto());
            ticketProvidersArray.add(ticketProviderObj);

            jsonResponse.put("ticketProvider", ticketProvidersArray);

            sendJsonResponse(response, jsonResponse);
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while creating JSON response");
        }
    }

    private void addTicketProvider(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        http://localhost:8080/ticketbox_war/ticketProviderController?mod=addticketProvider
//        {
//            "username": "ticketProvider02",
//            "password": "ticketProvider02",
//            "fullName": "ticketProvider02",
//            "email": "ticketProvider02@gmail.com",
//            "phoneNumber": "0990200391",
//            "position": "Đà nẵng",
//            "photo": "null",
//            "roleId": "3"
//        }
        try {
            // Lấy dữ liệu từ request
            String requestData = getRequestBody(request);
            JSONObject requestDataJson = (JSONObject) new JSONParser().parse(requestData);

            // Trích xuất thông tin từ dữ liệu JSON
            String username = (String) requestDataJson.get("username");
            String password = (String) requestDataJson.get("password");
            String fullName = (String) requestDataJson.get("fullName");
            String email = (String) requestDataJson.get("email");
            String phoneNumber = (String) requestDataJson.get("phoneNumber");
            String position = (String) requestDataJson.get("position");
            String providerName = (String) requestDataJson.get("providerName");
            String contactInfo = (String) requestDataJson.get("contactInfo");
            String photo = (String) requestDataJson.get("photo");
            String value = (String) requestDataJson.get("isConfirm");
            boolean isConfirm = value.equals("1");
            String roleId = (String) requestDataJson.get("roleId");

            long time = System.currentTimeMillis();
            String u_id = "U" + String.valueOf(time).substring(0, 9);
            String tp_id = "TP" + String.valueOf(time).substring(0, 8);
            System.out.println(u_id + "; " + tp_id + "; " + username + "; " + password + "; " + fullName);

            UserDAO userDAO = new UserDAO();
            Users user = new Users(u_id, username, password, roleId);
            userDAO.insert(user);

            // Tạo thông tin nhân viên mới và thêm vào CSDL
            TicketProviders newticketProvider = new TicketProviders(tp_id, u_id, fullName, email, phoneNumber, position, providerName, contactInfo, isConfirm, photo);
            TicketProvidersDAO ticketProviderDAO = new TicketProvidersDAO();
            ticketProviderDAO.insert(newticketProvider);

            // Gửi phản hồi JSON thành công
            sendSuccessResponse(response, "ticketProvider added successfully");
        } catch (ParseException e) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while adding ticketProvider");
        }
    }

    private void deleteTicketProvider(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    http://localhost:8080/ticketbox_war/ticketProviderController?mod=deleteticketProvider&ticketProviderId=10003
        try {
            // Lấy ticketProviderId từ URL
            String ticketProviderId = request.getParameter("ticketProviderId");

            if (ticketProviderId == null || ticketProviderId.isEmpty()) {
                sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "ticketProvider ID is required");
                return;
            }

            // Xóa nhân viên
            TicketProvidersDAO ticketProviderDAO = new TicketProvidersDAO();
            TicketProviders ticketProvider = ticketProviderDAO.selectById(ticketProviderId);

            UserDAO userDAO = new UserDAO();
            Users users = userDAO.selectById(ticketProvider.getUserId());

            ticketProviderDAO.delete(ticketProvider);
            userDAO.delete(users);

            // Gửi phản hồi JSON thành công
            sendSuccessResponse(response, "ticketProvider deleted successfully");
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while deleting ticketProvider");
        }
    }

    private void updateTicketProvider(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        http://localhost:8080/ticketbox_war/ticketProviderController?mod=updateticketProvider//
//        {
//            "fullName": "Nguyễn Phạm Nhật Vỹ",
//            "email": "nhatvy10@gmail.com",
//            "phoneNumber": "0900920002",
//            "position": "Hòa Quý - Đà nẵng",
//            "photo": "null",
//            "roleId": "2",
//            "ticketProviderId": "E17107332"
//        }
        try {
            // Lấy dữ liệu từ request
            String requestData = getRequestBody(request);
            JSONObject requestDataJson = (JSONObject) new JSONParser().parse(requestData);

            // Trích xuất thông tin từ dữ liệu JSON
            String ticketProviderId = (String) requestDataJson.get("ticketProviderId");
            String fullName = (String) requestDataJson.get("fullName");
            String email = (String) requestDataJson.get("email");
            String phoneNumber = (String) requestDataJson.get("phoneNumber");
            String position = (String) requestDataJson.get("position");
            String providerName = (String) requestDataJson.get("providerName");
            String contactInfo = (String) requestDataJson.get("contactInfo");
            String photo = (String) requestDataJson.get("photo");

            // Tạo đối tượng nhân viên và cập nhật vào CSDL
            TicketProviders updatedticketProvider = new TicketProviders();
            updatedticketProvider.setProvidersId(ticketProviderId);
            updatedticketProvider.setFullName(fullName);
            updatedticketProvider.setEmail(email);
            updatedticketProvider.setPhoneNumber(phoneNumber);
            updatedticketProvider.setPosition(position);
            updatedticketProvider.setProviderName(providerName);
            updatedticketProvider.setContactInfo(contactInfo);
            updatedticketProvider.setPhoto(photo);

            TicketProvidersDAO ticketProviderDAO = new TicketProvidersDAO();
            int result = ticketProviderDAO.update(updatedticketProvider);

            if(result!=0)
                // Gửi phản hồi JSON thành công
                sendSuccessResponse(response, "ticketProvider updated successfully");
            else
                sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while updating ticketProvider");
        } catch (ParseException e) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while updating ticketProvider");
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


//// Lấy ID = thời gian hiện tại tính bằng mili giây
//long time = System.currentTimeMillis();
//String timeString = String.valueOf(time);
//int length = timeString.length();
//String u_id = "U" + timeString.substring(length - 9); // Lấy 9 ký tự cuối cùng để tạo u_id
//String e_id = "E" + timeString.substring(length - 9); // Lấy 9 ký tự cuối cùng để tạo c_id
//            System.out.println(u_id + "; " + e_id + "; " + username + "; " + password + "; " + fullName);
