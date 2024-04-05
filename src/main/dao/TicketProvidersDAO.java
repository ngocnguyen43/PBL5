package main.dao;

import main.dao.interfaces.DAOInterface;
import main.model.TicketProviders;

import java.sql.*;
import java.util.ArrayList;

public class TicketProvidersDAO implements DAOInterface<TicketProviders> {

    @Override
    public ArrayList<TicketProviders> selectAll() {
        ArrayList<TicketProviders> result = new ArrayList<TicketProviders>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM ticketproviders";
            PreparedStatement st = con.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String providersId = rs.getString("providersId");
                String userId = rs.getString("user_id");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String position = rs.getString("position");
                String providerName = rs.getString("provider_name");
                String contactInfo = rs.getString("contact_info");
                Boolean isConfirmed = rs.getBoolean("is_confirmed");
                String photo = rs.getString("photo");

                TicketProviders s = new TicketProviders(providersId, userId, fullName, email, phoneNumber, position, providerName, contactInfo, isConfirmed, photo);
                result.add(s);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TicketProviders selectById(TicketProviders t) {
        TicketProviders result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM ticketproviders WHERE providers_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getProvidersId());

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String providersId = rs.getString("providersId");
                String userId = rs.getString("user_id");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String position = rs.getString("position");
                String providerName = rs.getString("provider_name");
                String contactInfo = rs.getString("contact_info");
                Boolean isConfirmed = rs.getBoolean("is_confirmed");
                String photo = rs.getString("photo");

                result = new TicketProviders(providersId, userId, fullName, email, phoneNumber, position, providerName, contactInfo, isConfirmed, photo);

                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean selectIsConfirmed() {
        Boolean result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT is_confirmed FROM ticketproviders ";
            PreparedStatement st = con.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                result = rs.getBoolean("is_confirmed");

                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public TicketProviders selectFromName(String name) {
        TicketProviders result = null;

        try {
            //Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();

            //Tạo ra đối tượng Statement 
            String sql = "SELECT * FROM ticketproviders WHERE full_name = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);

            //Thực thi câu lệnh SQL 
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //Lấy thông tin 
            while(rs.next()) {
                String providersId = rs.getString("providersId");
                String userId = rs.getString("user_id");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String position = rs.getString("position");
                String providerName = rs.getString("provider_name");
                String contactInfo = rs.getString("contact_info");
                Boolean isConfirmed = rs.getBoolean("is_confirmed");
                String photo = rs.getString("photo");

                result = new TicketProviders(providersId, userId, fullName, email, phoneNumber, position, providerName, contactInfo, isConfirmed, photo);

                break;
            }

            //Đóng cơ sở dữ liệu
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int insert(TicketProviders t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO ticketproviders (providers_id, user_id, full_name, email, phone_number, position, provider_name, contact_info, is_confirmed, photo) "+
                    " VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getProvidersId());
            st.setString(2, t.getUserId());
            st.setString(3, t.getFullName());
            st.setString(4, t.getEmail());
            st.setString(5, t.getPhoneNumber());
            st.setString(6, t.getPosition());
            st.setString(7, t.getProviderName());
            st.setString(8, t.getContactInfo());
            st.setBoolean(9, t.isConfirmed());
            st.setString(10, t.getPhoto());

            result = st.executeUpdate();

            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có "+ result+" dòng bị thay đổi!");

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int insertAll(ArrayList<TicketProviders> arr) {
        int count = 0;
        for (TicketProviders TicketProviders : arr) {
            count = count + this.insert(TicketProviders);
        }
        return count;
    }

    @Override
    public int delete(TicketProviders t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from ticketproviders "+
                    " WHERE providers_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getProvidersId());

            System.out.println(sql);
            result = st.executeUpdate();

            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có "+ result+" dòng bị thay đổi!");

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteAll(ArrayList<TicketProviders> arr) {
        int count = 0;
        for (TicketProviders TicketProviders : arr) {
            count = count + this.delete(TicketProviders);
        }
        return count;
    }

    @Override
    public int update(TicketProviders t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE ticketproviders "+
                    " SET " +
                    " full_name=?"+
                    ", email=?"+
                    ", phone_number=?"+
                    ", position=?"+
                    ", providerName=?"+
                    ", contactInfo=?"+
                    ", isConfirmed=?"+
                    ", photo=?"+
                    " WHERE providers_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getFullName());
            st.setString(2, t.getEmail());
            st.setString(3, t.getPhoneNumber());
            st.setString(4, t.getPosition());
            st.setString(5, t.getProviderName());
            st.setString(6, t.getContactInfo());
            st.setBoolean(7, t.isConfirmed());
            st.setString(8, t.getPhoto());
            st.setString(9, t.getProvidersId());


            System.out.println(sql);
            result = st.executeUpdate();

            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có "+ result+" dòng bị thay đổi!");

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}



