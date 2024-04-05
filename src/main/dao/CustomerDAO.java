package main.dao;

import main.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO implements DAOInterface<Customer> {

    @Override
    public ArrayList<Customer> selectAll() {
        ArrayList<Customer> result = new ArrayList<Customer>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM customer";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String customerId = rs.getString("customer_id");
                String userId = rs.getString("user_id");
                String fullname = rs.getString("full_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String position = rs.getString("position");
                String photo = rs.getString("photo");

                Customer cus = new Customer(customerId, userId, fullname, email, phoneNumber, position, photo);
                result.add(cus);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Customer selectById(Customer t) {
        Customer result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM customer WHERE customer_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getCustomerId());

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String customerId = rs.getString("customer_id");
                String userId = rs.getString("user_id");
                String fullname = rs.getString("full_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String position = rs.getString("position");
                String photo = rs.getString("photo");

                result = new Customer(customerId, userId, fullname, email, phoneNumber, position, photo);
                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Customer selectById(String t) {
        Customer result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            //Tạo ra đối tượng Statement
            String sql = "SELECT * FROM customer WHERE customer_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);

            //Thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //Lấy thông tin
            while(rs.next()) {
                String customerId = rs.getString("customer_id");
                String userId = rs.getString("user_id");
                String fullname = rs.getString("full_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String position = rs.getString("position");
                String photo = rs.getString("photo");

                result = new Customer(customerId, userId, fullname, email, phoneNumber, position, photo);

                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(Customer t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO customer (customer_id, user_id, full_name, email, phone_number, position) "+
                    " VALUES (?,?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getCustomerId());
            st.setString(2, t.getUserId());
            st.setString(3, t.getFullName());
            st.setString(4, t.getEmail());
            st.setString(5, t.getPhoneNumber());
            st.setString(6, t.getPosition());

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
    public int insertAll(ArrayList<Customer> arr) {
        int count = 0;
        for (Customer Customer : arr) {
            count = count + this.insert(Customer);
        }
        return count;
    }

    @Override
    public int delete(Customer t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from customer "+
                    " WHERE customer_id =?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getCustomerId());

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
    public int deleteAll(ArrayList<Customer> arr) {
        int count = 0;
        for (Customer Customer : arr) {
            count = count + this.delete(Customer);
        }
        return count;
    }

    @Override
    public int update(Customer t) {
        int result = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "UPDATE customer "+
                    " SET " +
                    " full_name=?"+
                    ", email=?"+
                    ", phone_number=?"+
                    ", position=?"+
                    " WHERE customer_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getFullName());
            st.setString(2, t.getEmail());
            st.setString(3, t.getPhoneNumber());
            st.setString(4, t.getPosition());
            st.setString(5, t.getCustomerId());

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

    //kiem tra Customername
    public boolean checkCustomername(String Customername) {
        boolean result = false;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM customer WHERE full_name=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, Customername);

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


//    Select Customer by ......
//    public Customer selectByUsernameAndPassword(Customer t) {
//        Customer result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM Customer WHERE username=? AND password=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getUsername());
//            st.setString(2, t.getPassword());
//
//            ResultSet rs = st.executeQuery();
//
//            //Lấy thông tin
//            while(rs.next()) {
//                String user_id = rs.getString("user_id");
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//                String role_id = rs.getString("role_id");
//
//                result = new Customer(user_id, username, password, role_id);
//                break;
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

}

