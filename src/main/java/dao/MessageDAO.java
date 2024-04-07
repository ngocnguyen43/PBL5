package dao;

public class MessageDAO {

//    @Override
//    public ArrayList<Message> selectAll() {
//        ArrayList<Message> result = new ArrayList<Message>();
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM message";
//            PreparedStatement st = con.prepareStatement(sql);
//
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            while(rs.next()) {
//                String messageId = rs.getString("message_id");
//                String senderId = rs.getString("sender_id");
//                String receiverId = rs.getString("receiver_id");
//                String messageContent = rs.getString("message_content");
//                Date messageDate = rs.getDate("message_date");
//                String messageType = rs.getString("message_type");
//
//                Message s = new Message(messageId, senderId, receiverId, messageContent, messageDate, messageType);
//                result.add(s);
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
//    public Message selectById(Message t) {
//        Message result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM message WHERE message_id=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getMessageId());
//
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            while(rs.next()) {
//                String messageId = rs.getString("message_id");
//                String senderId = rs.getString("sender_id");
//                String receiverId = rs.getString("receiver_id");
//                String messageContent = rs.getString("message_content");
//                Date messageDate = rs.getDate("message_date");
//                String messageType = rs.getString("message_type");
//
//                result = new Message(messageId, senderId, receiverId, messageContent, messageDate, messageType);
//
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
//    public Message selectBySenderId() {
//        Message result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM message ";
//            PreparedStatement st = con.prepareStatement(sql);
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//            while(rs.next()) {
//                String messageId = rs.getString("message_id");
//                String senderId = rs.getString("sender_id");
//                String receiverId = rs.getString("receiver_id");
//                String messageContent = rs.getString("message_content");
//                Date messageDate = rs.getDate("message_date");
//                String messageType = rs.getString("message_type");
//
//                result = new Message(messageId, senderId, receiverId, messageContent, messageDate, messageType);
//                break;
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//    public Message selectBySenderId(String senderID) {
//        Message result = null;
//
//        try {
//            //Kết nối với CSDL
//            Connection con = JDBCUtil.getConnection();
//
//            //Tạo ra đối tượng Statement
//            String sql = "SELECT * FROM message WHERE sender_id = ?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, senderID);
//
//            //Thực thi câu lệnh SQL
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            //Lấy thông tin
//            while(rs.next()) {
//                String messageId = rs.getString("message_id");
//                String senderId = rs.getString("sender_id");
//                String receiverId = rs.getString("receiver_id");
//                String messageContent = rs.getString("message_content");
//                Date messageDate = rs.getDate("message_date");
//                String messageType = rs.getString("message_type");
//
//                result = new Message(messageId, senderId, receiverId, messageContent, messageDate, messageType);
//                break;
//            }
//
//            //Đóng cơ sở dữ liệu
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    public Message selectByReceiverId(String receiverID) {
//        Message result = null;
//
//        try {
//            //Kết nối với CSDL
//            Connection con = JDBCUtil.getConnection();
//
//            //Tạo ra đối tượng Statement
//            String sql = "SELECT * FROM message WHERE receiver_id = ?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, receiverID);
//
//            //Thực thi câu lệnh SQL
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            //Lấy thông tin
//            while(rs.next()) {
//                String messageId = rs.getString("message_id");
//                String senderId = rs.getString("sender_id");
//                String receiverId = rs.getString("receiver_id");
//                String messageContent = rs.getString("message_content");
//                Date messageDate = rs.getDate("message_date");
//                String messageType = rs.getString("message_type");
//
//                result = new Message(messageId, senderId, receiverId, messageContent, messageDate, messageType);
//                break;
//            }
//
//            //Đóng cơ sở dữ liệu
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    @Override
//    public int insert(Message t) {
//        int result = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "INSERT INTO message (message_id, sender_id, receiver_id, message_content, message_date, message_type) "+
//                    " VALUES (?,?,?,?,?,?)";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getMessageId());
//            st.setString(2, t.getSenderId());
//            st.setString(3, t.getReceiverId());
//            st.setString(4, t.getMessageContent());
//            st.setDate(5, (Date) t.getMessageDate());
//            st.setString(6, t.getMessageType());
//
//            result = st.executeUpdate();
//
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
//    public int insertAll(ArrayList<Message> arr) {
//        int count = 0;
//        for (Message Message : arr) {
//            count = count + this.insert(Message);
//        }
//        return count;
//    }
//
//    @Override
//    public int delete(Message t) {
//        int result = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "DELETE from message "+
//                    " WHERE message_id=?";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getMessageId());
//
//            System.out.println(sql);
//            result = st.executeUpdate();
//
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
//    public int deleteAll(ArrayList<Message> arr) {
//        int count = 0;
//        for (Message Message : arr) {
//            count = count + this.delete(Message);
//        }
//        return count;
//    }
//
//    @Override
//    public int update(Message t) {
//        int result = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "UPDATE message "+
//                    " SET " +
//                    " messageContent=?"+
//                    " WHERE message_id=?";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getMessageContent());
//            st.setString(2, t.getMessageId());
//
//
//            System.out.println(sql);
//            result = st.executeUpdate();
//
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
}


