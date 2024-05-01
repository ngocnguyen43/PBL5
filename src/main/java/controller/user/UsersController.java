package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/userController")
public class userControllerApi extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public userControllerApi() {
        super();
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String mod = request.getParameter("mod");
//        if (mod != null) {
//            switch (mod) {
//                case "selectAllUser":
//                    selectAllUser(request, response);
//                    break;
//                case "getInfoUser":
//                    getInfoUser(request, response);
//                    break;
//                case "addUser":
//                    addUser(request, response);
//                    break;
//                case "deleteUser":
//                    deleteUser(request, response);
//                    break;
//                case "updateUser":
//                    updateUser(request, response);
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
//    private void selectAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
////    http://localhost:8080/ticketbox_war/userController?mod=selectAllUser
//        UserDAO userDAO = new UserDAO();
//        ArrayList<Users> userList = userDAO.selectAll();
//        try {
//            JSONObject jsonResponse = new JSONObject();
//            jsonResponse.put("status", "success");
//
//            JSONArray usersArray = new JSONArray();
//            for (Users user : userList) {
//                JSONObject userObj = new JSONObject();
//                userObj.put("username", user.getUsername());
//                userObj.put("password", user.getPassword());
//                usersArray.add(userObj);
//            }
//
//            jsonResponse.put("users", usersArray);
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
//    private void getInfoUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
////    http://localhost:8080/ticketbox_war/userController?mod=getInfoUser&userId=10003
//        // Lấy user_id từ URL
//        String userId = request.getParameter("userId");
//        System.out.println("User ID: " + userId);
//
//        if (userId == null || userId.isEmpty()) {
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
//        UserDAO userDAO = new UserDAO();
//        Users user = userDAO.selectById(userId);
//
//        if (user == null) {
//            error += "Khong ton tai user co id="+userId+"!!!";
//            jsonResponse.put("status", "error");
//            jsonResponse.put("value", error);
//            System.out.println("khong ton tai user co id="+userId+"!!!");
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(jsonResponse.toString());
//            return;
//        }
//
//        try {
//            jsonResponse.put("status", "success");
//
//            JSONArray usersArray = new JSONArray();
//            JSONObject userObj = new JSONObject();
//            userObj.put("userId", user.getUserId());
//            userObj.put("username", user.getUsername());
//            userObj.put("password", user.getPassword());
//            userObj.put("roleId", user.getRoleId());
//            usersArray.add(userObj);
//
//            jsonResponse.put("users", usersArray);
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
//    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        http://localhost:8080/ticketbox_war/userController?mod=addUser
////        {
////            "username": "customer001",
////                "password": "customer001",
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
//        String role = (String) requestData.get("roleId");
//
//        long time = System.currentTimeMillis();
//        String id = "U" + String.valueOf(time).substring(0, 8);
//        System.out.println(id + "; " + username + "; " + password + "; " + role);
//
//        String error = "";
//        String url = "";
//        UserDAO userDAO = new UserDAO();
//
//        JSONObject jsonResponse = new JSONObject();
//        if (userDAO.checkUsersnameDiferentId(username, id)) {
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
//            request.setAttribute("userId", id);
//
//            jsonResponse.put("status", "error");
//            jsonResponse.put("value", "addUser khong thanh cong");
//            System.out.println("addUser không thành công");
////            url = "/userAdd.jsp";
//        } else {
//            Users user = new Users(id, username, password, role);
//            userDAO.insert(user);
//
//            jsonResponse.put("status", "success");
//            System.out.println("Add User thành công");
////            url = "/user_manage.jsp";
//        }
//
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        out.print(jsonResponse);
//        out.flush();
//    }
//
//    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        // Xóa người dùng và trả về kết quả dưới dạng JSON
//        //    http://localhost:8080/ticketbox_war/userController?mod=getInfoUser&userId=10003
//        // Lấy user_id từ URL
//        String userId = request.getParameter("userId");
//        System.out.println("User ID: " + userId);
//
//        if (userId == null || userId.isEmpty()) {
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
//        UserDAO userDAO = new UserDAO();
//        Users user = new Users();
//        user.setUserId(userId);
//
//        int result = userDAO.delete(user);
//
//        if (result==0) {
//            error += "Không được phép xoá vì user này liên quan đến các thông tin khác.";
//            jsonResponse.put("status", "error");
//            jsonResponse.put("value", error);
//            System.out.println("Không được phép xoá vì user này liên quan đến các thông tin khác.");
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(jsonResponse.toString());
//            return;
//        }
//
//        try {
//            jsonResponse.put("status", "success");
//            jsonResponse.put("value", "Xoa user thanh cong");
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
//    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
//        String username = (String) requestData.get("username");
//        String password = (String) requestData.get("password");
//        String role = (String) requestData.get("roleId");
//        String id = (String) requestData.get("userId");
//        System.out.println(id + "; " + username + "; " + password + "; " + role);
//
//        String error = "";
//        String url = "";
//        UserDAO userDAO = new UserDAO();
//
//        JSONObject jsonResponse = new JSONObject();
//
//        if (userDAO.checkUsersnameDiferentId(username, id)) {
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
//            request.setAttribute("userId", id);
//
//            jsonResponse.put("status", "error");
//            jsonResponse.put("value", "update khong thanh cong");
//            System.out.println("Update User không thành công");
//            url = "/userUpdate.jsp";
//        } else {
//            Users user = new Users(id, username, password, role);
//            userDAO.update(user);
//
//            jsonResponse.put("status", "success");
//            System.out.println("Update User thành công");
//            url = "/user_manage.jsp";
//        }
//
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        out.print(jsonResponse);
//        out.flush();
//    }
}
