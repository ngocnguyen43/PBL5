package main.dao;

import main.model.SeatPositions;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class SeatPositionsDAO implements DAOInterface<SeatPositions> {

    @Override
    public ArrayList<SeatPositions> selectAll() {
        ArrayList<SeatPositions> result = new ArrayList<SeatPositions>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM seatpositions";
            PreparedStatement st = con.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String seatId = rs.getString("seat_id");
                String scheduleId = rs.getString("schedule_id");
                int seatNumber = rs.getInt("seat_number");
                BigDecimal price = rs.getBigDecimal("price");
                String status = rs.getString("status");

                SeatPositions s = new SeatPositions(seatId, scheduleId, seatNumber, price, status);
                result.add(s);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SeatPositions selectById(SeatPositions t) {
        SeatPositions result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM seatpositions WHERE seat_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getSeatId());

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String seatId = rs.getString("seat_id");
                String scheduleId = rs.getString("schedule_id");
                int seatNumber = rs.getInt("seat_number");
                BigDecimal price = rs.getBigDecimal("price");
                String status = rs.getString("status");

                result = new SeatPositions(seatId, scheduleId, seatNumber, price, status);
                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public SeatPositions selectfullname() {
//        SeatPositions result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT full_name FROM SeatPositions ";
//            PreparedStatement st = con.prepareStatement(sql);
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//            while(rs.next()) {
//                String employeeId = rs.getString("employee_id");
//                String userId = rs.getString("user_id");
//                String fullName = rs.getString("full_name");
//                String gender = rs.getString("gender");
//                String email = rs.getString("email");
//                String phoneNumber = rs.getString("phone_number");
//                String position = rs.getString("position");
//                Date date_of_birth = rs.getDate("date_of_birth");
//
//                result = new SeatPositions(employeeId, userId, fullName, gender, email, phoneNumber, position, date_of_birth);
//                break;
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    public SeatPositions selectFromSeatNumber(String sche_Id, int number) {
        SeatPositions result = null;

        try {
            //Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();

            //Tạo ra đối tượng Statement
            String sql = "SELECT * FROM seatpositions WHERE schedule_id = ? AND seat_number = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, sche_Id);
            st.setInt(2, number);

            //Thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //Lấy thông tin
            while(rs.next()) {
                String seatId = rs.getString("seat_id");
                String scheduleId = rs.getString("schedule_id");
                int seatNumber = rs.getInt("seat_number");
                BigDecimal price = rs.getBigDecimal("price");
                String status = rs.getString("status");

                result = new SeatPositions(seatId, scheduleId, seatNumber, price, status);
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
    public int insert(SeatPositions t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO seatpositions (seat_id, schedule_id, seat_number, price, status) "+
                    " VALUES (?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getSeatId());
            st.setString(2, t.getScheduleId());
            st.setInt(3, t.getSeatNumber());
            st.setBigDecimal(4, t.getPrice());
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
    public int insertAll(ArrayList<SeatPositions> arr) {
        int count = 0;
        for (SeatPositions SeatPositions : arr) {
            count = count + this.insert(SeatPositions);
        }
        return count;
    }

    @Override
    public int delete(SeatPositions t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from seatpositions "+
                    " WHERE seat_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getSeatId());

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
    public int deleteAll(ArrayList<SeatPositions> arr) {
        int count = 0;
        for (SeatPositions SeatPositions : arr) {
            count = count + this.delete(SeatPositions);
        }
        return count;
    }

    @Override
    public int update(SeatPositions t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE SeatPositions "+
                    " SET " +
                    " seat_number=?"+
                    ", price=?"+
                    ", status=?"+
                    " WHERE seat_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getSeatNumber());
            st.setBigDecimal(2, t.getPrice());
            st.setString(3, t.getStatus());
            st.setString(4, t.getSeatId());


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


