package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testConnect {
//    public static void main(String[] args) {
//        Users result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM users WHERE user_id=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, "10001"); // Đặt user_id tại đây
//
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                String id = rs.getString("user_id");
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//                String role = rs.getString("role_id");
//
//                System.out.println(id+""+ username+""+ password+""+""+ role);
//                break;
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
