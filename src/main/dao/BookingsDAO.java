package main.dao;

import main.model.Booking;

import java.sql.*;
import java.util.ArrayList;

public class BookingsDAO implements DAOInterface<Booking> {

    @Override
    public ArrayList<Booking> selectAll() {
        ArrayList<Booking> result = new ArrayList<Booking>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM bookings";
            PreparedStatement st = con.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String bookingId = rs.getString("booking_id");
                String userId = rs.getString("user_id");
                String ticketId = rs.getString("ticket_id");
                Date bookingDate = rs.getDate("booking_date");
                String status = rs.getString("status");

                Booking s = new Booking(bookingId, userId, ticketId, bookingDate, status);
                result.add(s);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Booking selectById(Booking t) {
        Booking result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM bookings WHERE booking_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getBookingId());

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String bookingId = rs.getString("booking_id");
                String userId = rs.getString("user_id");
                String ticketId = rs.getString("ticket_id");
                Date bookingDate = rs.getDate("booking_date");
                String status = rs.getString("status");

                result = new Booking(bookingId, userId, ticketId, bookingDate, status);
                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String selectTicketId() {
        Booking result = null;
        String ticketId = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT ticket_id FROM bookings ";
            PreparedStatement st = con.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                ticketId = rs.getString("ticket_id");
//                String userId = rs.getString("user_id");
//                String fullName = rs.getString("full_name");
//                String gender = rs.getString("gender");
//                String email = rs.getString("email");
//                String phoneNumber = rs.getString("phone_number");
//                String position = rs.getString("position");
//                Date date_of_birth = rs.getDate("date_of_birth");

//                result = new Bookings(employeeId, userId, fullName, gender, email, phoneNumber, position, date_of_birth);

                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketId;
    }
    public Booking selectByUserId(String user_id) {
        Booking result = null;

        try {
            //Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();

            //Tạo ra đối tượng Statement 
            String sql = "SELECT * FROM bookings WHERE user_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user_id);

            //Thực thi câu lệnh SQL 
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //Lấy thông tin 
            while(rs.next()) {
                String bookingId = rs.getString("booking_id");
                String userId = rs.getString("user_id");
                String ticketId = rs.getString("ticket_id");
                Date bookingDate = rs.getDate("booking_date");
                String status = rs.getString("status");

                result = new Booking(bookingId, userId, ticketId, bookingDate, status);
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
    public int insert(Booking t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO bookings (booking_id, user_id, ticket_id, booking_date, status) "+
                    " VALUES (?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getBookingId());
            st.setString(2, t.getUserId());
            st.setString(3, t.getTicketId());
            st.setDate(4, (Date) t.getBookingDate());
            st.setString(5, t.getStatus());

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
    public int insertAll(ArrayList<Booking> arr) {
        int count = 0;
        for (Booking Booking : arr) {
            count = count + this.insert(Booking);
        }
        return count;
    }

    @Override
    public int delete(Booking t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from bookings "+
                    " WHERE booking_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getBookingId());

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
    public int deleteAll(ArrayList<Booking> arr) {
        int count = 0;
        for (Booking Booking : arr) {
            count = count + this.delete(Booking);
        }
        return count;
    }

    @Override
    public int update(Booking t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();


//            UPDATE bookings SET booking_date='2024-02-21 10:08:00', status='Confirmed' WHERE booking_id=80001;

            String sql = "UPDATE bookings "+
                    " SET " +
                    " booking_date=?"+
                    ", status=?"+
                    " WHERE booking_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setDate(1, (Date) t.getBookingDate());
            st.setString(2, t.getStatus());


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



