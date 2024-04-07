package org.example.ticketbox.database;

import org.example.ticketbox.Model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements DAOInterface<Users> {

    @Override
    public ArrayList<Users> selectAll() {
        ArrayList<Users> result = new ArrayList<Users>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM users";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String userId = rs.getString("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String roleId = rs.getString("role_id");

                Users u = new Users(userId, username, password, roleId);
                result.add(u);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Users selectById(Users t) {
        Users result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM users WHERE user_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUserId());

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String user_id = rs.getString("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role_id = rs.getString("role_id");

                result = new Users(user_id, username, password, role_id);
                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Users selectById(String t) {
        Users result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            //Tạo ra đối tượng Statement 
            String sql = "SELECT * FROM users WHERE user_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);

            //Thực thi câu lệnh SQL 
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //Lấy thông tin 
            while(rs.next()) {
                String userId = rs.getString("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String roleId = rs.getString("role_id");

                result = new Users(userId, username, password, roleId);
                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(Users t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO users (user_id, username, password, role_id) "+
                    " VALUES (?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUserId());
            st.setString(2, t.getUsername());
            st.setString(3, t.getPassword());
            st.setString(4, t.getRoleId());

            // Bước 3: thực thi câu lệnh SQL
            result = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có "+ result+" dòng bị thay đổi!");

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int insertAll(ArrayList<Users> arr) {
        int count = 0;
        for (Users Users : arr) {
            count = count + this.insert(Users);
        }
        return count;
    }

    @Override
    public int delete(Users t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from users "+
                    " WHERE user_id =?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUserId());

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            result = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có "+ result+" dòng bị thay đổi!");

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteAll(ArrayList<Users> arr) {
        int count = 0;
        for (Users Users : arr) {
            count = count + this.delete(Users);
        }
        return count;
    }

    @Override
    public int update(Users t) {
        int result = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "UPDATE users "+
                    " SET " +
                    " username=?"+
                    ", password=?"+
                    ", role_id=?"+
                    " WHERE user_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUsername());
            st.setString(2, t.getPassword());
            st.setString(3, t.getRoleId());
            st.setString(4, t.getUserId());

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            result = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có "+ result+" dòng bị thay đổi!");

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //kiem tra Usersname
    public boolean checkUsersname(String Usersname) {
        boolean result = false;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM users WHERE username=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, Usersname);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                return true;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkUsersnameDiferentId(String Username, String UserId) {
        boolean result = false;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND user_id <> ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, Username);
            st.setString(2, UserId);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                return true;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public Users selectByUsernameAndPassword(Users t) {
        Users result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUsername());
            st.setString(2, t.getPassword());

            ResultSet rs = st.executeQuery();

            //Lấy thông tin 
            while(rs.next()) {
                String user_id = rs.getString("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role_id = rs.getString("role_id");

                result = new Users(user_id, username, password, role_id);
                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}

