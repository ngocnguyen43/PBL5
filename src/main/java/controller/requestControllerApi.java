package org.example.ticketbox.Controller;

import org.example.ticketbox.Model.ScheduleRequest;
import org.example.ticketbox.database.ScheduleRequestDAO;
import org.example.ticketbox.Model.Schedules;
import org.example.ticketbox.database.SchedulesDAO;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/requestController")
public class requestControllerApi extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public requestControllerApi() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mod = request.getParameter("mod");
        if (mod != null) {
            switch (mod) {
                case "selectAllRequest":
                    selectAllRequest(request, response);
                    break;
                case "getInfoRequest":
                    getInfoRequest(request, response);
                    break;
                case "addRequest":
                    addRequest(request, response);
                    break;
                case "deleteRequest":
                    deleteRequest(request, response);
                    break;
                case "updateRequest":
                    updateRequest(request, response);
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

    private void selectAllRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
////    http://localhost:8080/ticketbox_war/RequestController?mod=selectAllRequest
        ScheduleRequestDAO RequestDAO = new ScheduleRequestDAO();
        ArrayList<ScheduleRequest> RequestList = RequestDAO.selectAll();
        try {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");

            JSONArray RequestsArray = new JSONArray();
            for (ScheduleRequest Request : RequestList) {
                JSONObject RequestObj = new JSONObject();
                RequestObj.put("requestId", Request.getRequestId());
                RequestObj.put("providerId", Request.getProviderId());
                RequestObj.put("submissionDate", Request.getSubmissionDate());
                RequestObj.put("status", Request.getStatus());
                RequestObj.put("requestDetail", Request.getRequestDetails());
                RequestsArray.add(RequestObj);
            }

            jsonResponse.put("Request", RequestsArray);

            sendJsonResponse(response, jsonResponse);
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while creating JSON response");
        }
    }

    private void getInfoRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
////    http://localhost:8080/ticketbox_war/RequestController?mod=getInfoRequest&RequestId=10002
        String RequestId = request.getParameter("RequestId");

        if (RequestId == null || RequestId.isEmpty()) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Request ID is required");
            return;
        }

        ScheduleRequestDAO RequestDAO = new ScheduleRequestDAO();
        ScheduleRequest Request = RequestDAO.selectById(RequestId);

        SchedulesDAO scheduleDAO = new SchedulesDAO();
        Schedules schedules = scheduleDAO.selectByRequestId(RequestId);

        if (Request == null || schedules==null) {
            sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "Request not found");
            return;
        }

        try {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");

            JSONArray RequestsArray = new JSONArray();
            JSONObject RequestObj = new JSONObject();
            RequestObj.put("requestId", Request.getRequestId());
            RequestObj.put("providerId", Request.getProviderId());
            RequestObj.put("submissionDate", Request.getSubmissionDate());
            RequestObj.put("status", Request.getStatus());
            RequestObj.put("requestDetail", Request.getRequestDetails());

            RequestObj.put("tripCode", schedules.getTripCode());
            RequestObj.put("departureId", schedules.getDeparturePoint());
            RequestObj.put("arrivalId", schedules.getArrivalPoint());
            RequestObj.put("departureDatetime", schedules.getDepartureDatetime());
            RequestObj.put("estimatedTravelTime", schedules.getEstimatedTravelTime());
            RequestObj.put("seatCapacity", schedules.getSeatCapacity());
            RequestObj.put("trainCarNumber", schedules.getTrainCarNumber());
            RequestObj.put("seatPrice", schedules.getSeatPrice());
            RequestObj.put("notes", schedules.getNotes());
            RequestObj.put("status", schedules.getStatus());

            RequestsArray.add(RequestObj);

            jsonResponse.put("Request", RequestsArray);

            sendJsonResponse(response, jsonResponse);
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while creating JSON response");
        }
    }

    private void addRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        http://localhost:8080/ticketbox_war/RequestController?mod=addRequest
//        {
//            "username": "Request02",
//            "password": "Request02",
//            "fullName": "Request02",
//            "email": "Request02@gmail.com",
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
            String providerId = (String) requestDataJson.get("providerId");
            String submissionDate = (String) requestDataJson.get("submissionDate");
            String requestDetail = (String) requestDataJson.get("requestDetail");
            String tripCode = (String) requestDataJson.get("tripCode");
            String departureId = (String) requestDataJson.get("departureId");
            String arrivalId = (String) requestDataJson.get("arrivalId");
            String departureDatetime = (String) requestDataJson.get("departureDatetime");
            //'2024-03-15 08:00:00'
            Integer estimatedTravelTime = (Integer) requestDataJson.get("estimatedTravelTime");
            Integer seatCapacity = (Integer) requestDataJson.get("seatCapacity");
            Integer trainCarNumber = (Integer) requestDataJson.get("trainCarNumber");
            BigDecimal seatPrice = (BigDecimal) requestDataJson.get("seatPrice");
            String notes = (String) requestDataJson.get("notes");

            long time = System.currentTimeMillis();
            String re_id = "RE" + String.valueOf(time).substring(0, 8);
            String sc_id = "SC" + String.valueOf(time).substring(0, 8);
            System.out.println(re_id + "; " + sc_id + "; ");

//          Format Date SQL;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = dateFormat.parse(submissionDate);
            } catch (java.text.ParseException e) {
                throw new RuntimeException(e);
            }
            java.sql.Date submitDate = new java.sql.Date(date.getTime());

//          Status:  'Approved',    'Pending'
//                   'Đã phê duyệt','Đang chờ xử lý'

            ScheduleRequestDAO requestDAO = new ScheduleRequestDAO();
            ScheduleRequest schRequest = new ScheduleRequest(re_id, providerId, submitDate, "Pending", requestDetail);
            requestDAO.insert(schRequest);

            // Tạo thông tin nhân viên mới và thêm vào CSDL
            //          Format Date SQL;
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"); // Sửa định dạng ở đây
            Date dateTime = null;
            try {
                dateTime = dateFormat.parse(submissionDate);
            } catch (java.text.ParseException e) {
                throw new RuntimeException(e);
            }
            java.sql.Date sqlDateTime = new java.sql.Date(dateTime.getTime()); // Đổi tên biến để tránh xung đột

            SchedulesDAO schedulesDAO = new SchedulesDAO();
            Schedules schedules = new Schedules(sc_id, providerId, re_id, tripCode, departureId, arrivalId, sqlDateTime,
                    estimatedTravelTime, seatCapacity, trainCarNumber, seatPrice, notes, "Pending");

            schedulesDAO.insert(schedules);

            // Gửi phản hồi JSON thành công
            sendSuccessResponse(response, "Request added successfully");
        } catch (ParseException e) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while adding Request");
        }
    }

    private void deleteRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    http://localhost:8080/ticketbox_war/RequestController?mod=deleteRequest&RequestId=10003
        try {
            // Lấy RequestId từ URL
            String RequestId = request.getParameter("RequestId");

            if (RequestId == null || RequestId.isEmpty()) {
                sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Request ID is required");
                return;
            }

            // Xóa nhân viên
            SchedulesDAO schedulesDAO = new SchedulesDAO();
            ScheduleRequestDAO scheduleRequestDAO = new ScheduleRequestDAO();

            Schedules schedules = schedulesDAO.selectByRequestId(RequestId);
            schedulesDAO.delete(schedules);

            ScheduleRequest request1 = scheduleRequestDAO.selectById(RequestId);
            scheduleRequestDAO.delete(request1);

            // Gửi phản hồi JSON thành công
            sendSuccessResponse(response, "Request deleted successfully");
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while deleting Request");
        }
    }

    private void updateRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        http://localhost:8080/ticketbox_war/RequestController?mod=updateRequest//
//        {
//            "fullName": "Nguyễn Phạm Nhật Vỹ",
//            "email": "nhatvy10@gmail.com",
//            "phoneNumber": "0900920002",
//            "position": "Hòa Quý - Đà nẵng",
//            "photo": "null",
//            "roleId": "2",
//            "RequestId": "E17107332"
//        }
        try {
            // Lấy dữ liệu từ request
            String requestData = getRequestBody(request);
            JSONObject requestDataJson = (JSONObject) new JSONParser().parse(requestData);

            // Trích xuất thông tin từ dữ liệu JSON
            String RequestId = (String) requestDataJson.get("RequestId");
            String fullName = (String) requestDataJson.get("fullName");
            String email = (String) requestDataJson.get("email");
            String phoneNumber = (String) requestDataJson.get("phoneNumber");
            String position = (String) requestDataJson.get("position");
            String photo = (String) requestDataJson.get("photo");

            // Tạo đối tượng nhân viên và cập nhật vào CSDL
//            Request updatedRequest = new Request();
//            updatedRequest.setRequestId(RequestId);
//
//            RequestDAO RequestDAO = new RequestDAO();
//            int result = RequestDAO.update(updatedRequest);

//            if(result!=0)
//                // Gửi phản hồi JSON thành công
//                sendSuccessResponse(response, "Request updated successfully");
//            else
//                sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while updating Request");
        } catch (ParseException e) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while updating Request");
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
