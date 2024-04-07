package dao;

public class ScheduleRequestDAO  {

//    @Override
//    public ArrayList<ScheduleRequest> selectAll() {
//        ArrayList<ScheduleRequest> result = new ArrayList<ScheduleRequest>();
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM schedulerequest";
//            PreparedStatement st = con.prepareStatement(sql);
//
//            ResultSet rs = st.executeQuery();
//
//            while(rs.next()) {
//                String requestId = rs.getString("request_id");
//                String providerId = rs.getString("provider_id");
//                Date submissionDate = rs.getDate("submission_date");
//                String status = rs.getString("email");
//                String requestDetails = rs.getString("request_details");
//
//                ScheduleRequest request = new ScheduleRequest(requestId, providerId, submissionDate, status, requestDetails);
//                result.add(request);
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public ScheduleRequest selectById(ScheduleRequest t) {
//        ScheduleRequest result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM schedulerequest WHERE request_id=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getRequestId());
//
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            while(rs.next()) {
//                String requestId = rs.getString("request_id");
//                String providerId = rs.getString("provider_id");
//                Date submissionDate = rs.getDate("submission_date");
//                String status = rs.getString("email");
//                String requestDetails = rs.getString("request_details");
//
//                result = new ScheduleRequest(requestId, providerId, submissionDate, status, requestDetails);
//                break;
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public ScheduleRequest selectById(String t) {
//        ScheduleRequest result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            //Tạo ra đối tượng Statement
//            String sql = "SELECT * FROM schedulerequest WHERE request_id=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t);
//
//            //Thực thi câu lệnh SQL
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            //Lấy thông tin
//            while(rs.next()) {
//                String requestId = rs.getString("request_id");
//                String providerId = rs.getString("provider_id");
//                Date submissionDate = rs.getDate("submission_date");
//                String status = rs.getString("email");
//                String requestDetails = rs.getString("request_details");
//
//                result = new ScheduleRequest(requestId, providerId, submissionDate, status, requestDetails);
//                break;
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public int insert(ScheduleRequest t) {
//        int result = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "INSERT INTO schedulerequest (request_id, provider_id, submission_date, status, request_details) "+
//                    " VALUES (?,?,?,?,?)";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getRequestId());
//            st.setString(2, t.getProviderId());
//            st.setDate(3, (java.sql.Date) t.getSubmissionDate());
//            st.setString(4, t.getStatus());
//            st.setString(5, t.getRequestDetails());
//
//            // Bước 3: thực thi câu lệnh SQL
//            result = st.executeUpdate();
//
//            // Bước 4:
//            System.out.println("Bạn đã thực thi: "+ sql);
//            System.out.println("Có "+ result+" dòng bị thay đổi!");
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    @Override
//    public int insertAll(ArrayList<ScheduleRequest> arr) {
//        int count = 0;
//        for (ScheduleRequest ScheduleRequest : arr) {
//            count = count + this.insert(ScheduleRequest);
//        }
//        return count;
//    }
//
//    @Override
//    public int delete(ScheduleRequest t) {
//        int result = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "DELETE from schedulerequest "+
//                    " WHERE request_id =?";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getRequestId());
//
//            // Bước 3: thực thi câu lệnh SQL
//            System.out.println(sql);
//            result = st.executeUpdate();
//
//            // Bước 4:
//            System.out.println("Bạn đã thực thi: "+ sql);
//            System.out.println("Có "+ result+" dòng bị thay đổi!");
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    @Override
//    public int deleteAll(ArrayList<ScheduleRequest> arr) {
//        int count = 0;
//        for (ScheduleRequest ScheduleRequest : arr) {
//            count = count + this.delete(ScheduleRequest);
//        }
//        return count;
//    }
//
//    @Override
//    public int update(ScheduleRequest t) {
//        int result = 0;
//        try {
//            // Bước 1: tạo kết nối đến CSDL
//            Connection con = JDBCUtil.getConnection();
//
//            // Bước 2: tạo ra đối tượng statement
//            String sql = "UPDATE schedulerequest "+
//                    " SET " +
//                    " status=?"+
//                    ", requestDetails=?"+
//                    " WHERE request_id=?";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getStatus());
//            st.setString(2, t.getRequestDetails());
//            st.setString(3, t.getRequestId());
//
//            // Bước 3: thực thi câu lệnh SQL
//            System.out.println(sql);
//            result = st.executeUpdate();
//
//            // Bước 4:
//            System.out.println("Bạn đã thực thi: "+ sql);
//            System.out.println("Có "+ result+" dòng bị thay đổi!");
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    //kiem tra ScheduleRequestename
////    public boolean checkScheduleRequest(String ScheduleRequestename) {
////        boolean result = false;
////        try {
////            Connection con = JDBCUtil.getConnection();
////
////            String sql = "SELECT * FROM ScheduleRequeste WHERE full_name=?";
////
////            PreparedStatement st = con.prepareStatement(sql);
////            st.setString(1, ScheduleRequestename);
////
////            System.out.println(sql);
////            ResultSet rs = st.executeQuery();
////
////            while(rs.next()) {
////                return true;
////            }
////
////            JDBCUtil.closeConnection(con);
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return result;
////    }
//
//
////    Select ScheduleRequeste by ......
////    public ScheduleRequeste selectByUsernameAndPassword(ScheduleRequeste t) {
////        ScheduleRequeste result = null;
////        try {
////            Connection con = JDBCUtil.getConnection();
////
////            String sql = "SELECT * FROM ScheduleRequeste WHERE username=? AND password=?";
////            PreparedStatement st = con.prepareStatement(sql);
////            st.setString(1, t.getUsername());
////            st.setString(2, t.getPassword());
////
////            ResultSet rs = st.executeQuery();
////
////            //Lấy thông tin
////            while(rs.next()) {
////                String user_id = rs.getString("user_id");
////                String username = rs.getString("username");
////                String password = rs.getString("password");
////                String role_id = rs.getString("role_id");
////
////                result = new ScheduleRequeste(user_id, username, password, role_id);
////                break;
////            }
////
////            JDBCUtil.closeConnection(con);
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return result;
////    }

}


