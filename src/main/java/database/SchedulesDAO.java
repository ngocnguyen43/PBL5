package org.example.ticketbox.database;

import org.example.ticketbox.Model.Schedules;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class SchedulesDAO implements DAOInterface<Schedules> {

    @Override
    public ArrayList<Schedules> selectAll() {
        ArrayList<Schedules> result = new ArrayList<Schedules>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM schedules";
            PreparedStatement st = con.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String scheduleId = rs.getString("employee_id");
                String providerId = rs.getString("provider_id");
                String requestId = rs.getString("request_id");
                String tripCode = rs.getString("trip_code");
                String departurePoint = rs.getString("departure_point");
                String arrivalPoint = rs.getString("arrival_point");
                Date departureDatetime = rs.getDate("departure_datetime");
                int estimatedTravelTime = rs.getInt("estimated_travel_time");
                int seatCapacity = rs.getInt("seat_capacity");
                int trainCarNumber = rs.getInt("train_car_number");
                BigDecimal seatPrice = rs.getBigDecimal("seat_price");
                String notes = rs.getString("notes");

                Schedules s = new Schedules(scheduleId, providerId, requestId, tripCode, departurePoint, arrivalPoint, departureDatetime, estimatedTravelTime, seatCapacity, trainCarNumber, seatPrice, notes, "Pending");
                result.add(s);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Schedules selectById(Schedules t) {
        Schedules result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM schedules WHERE schedule_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getScheduleId());

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String scheduleId = rs.getString("schedule_id");
                String providerId = rs.getString("provider_id");
                String requestId = rs.getString("request_id");
                String tripCode = rs.getString("trip_code");
                String departurePoint = rs.getString("departure_point");
                String arrivalPoint = rs.getString("arrival_point");
                Date departureDatetime = rs.getDate("departure_datetime");
                int estimatedTravelTime = rs.getInt("estimated_travel_time");
                int seatCapacity = rs.getInt("seat_capacity");
                int trainCarNumber = rs.getInt("train_car_number");
                BigDecimal seatPrice = rs.getBigDecimal("seat_price");
                String notes = rs.getString("notes");

                result = new Schedules(scheduleId, providerId, requestId, tripCode, departurePoint, arrivalPoint, departureDatetime, estimatedTravelTime, seatCapacity, trainCarNumber, seatPrice, notes, "Pending");
                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Schedules selectByRequestId(String t) {
        Schedules result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM schedules WHERE request_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String scheduleId = rs.getString("schedule_id");
                String providerId = rs.getString("provider_id");
                String requestId = rs.getString("request_id");
                String tripCode = rs.getString("trip_code");
                String departurePoint = rs.getString("departure_point");
                String arrivalPoint = rs.getString("arrival_point");
                Date departureDatetime = rs.getDate("departure_datetime");
                int estimatedTravelTime = rs.getInt("estimated_travel_time");
                int seatCapacity = rs.getInt("seat_capacity");
                int trainCarNumber = rs.getInt("train_car_number");
                BigDecimal seatPrice = rs.getBigDecimal("seat_price");
                String notes = rs.getString("notes");

                result = new Schedules(scheduleId, providerId, requestId, tripCode, departurePoint, arrivalPoint, departureDatetime, estimatedTravelTime, seatCapacity, trainCarNumber, seatPrice, notes, "Pending");
                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String selectDeparturePoint() {
        String result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT departure_point FROM schedules ";
            PreparedStatement st = con.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                result = rs.getString("departure_point");

                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String selectArrivalPoint() {
        String result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT arrival_point FROM schedules ";
            PreparedStatement st = con.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                result = rs.getString("arrival_point");

                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<Schedules> selectFromProviderId(String provider_id) {
        ArrayList<Schedules> result = new ArrayList<Schedules>();

        try {
            //Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();

            //Tạo ra đối tượng Statement 
            String sql = "SELECT * FROM schedules WHERE provider_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, provider_id);

            //Thực thi câu lệnh SQL 
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //Lấy thông tin 
            while(rs.next()) {
                String scheduleId = rs.getString("schedule_id");
                String providerId = rs.getString("provider_id");
                String requestId = rs.getString("request_id");
                String tripCode = rs.getString("trip_code");
                String departurePoint = rs.getString("departure_point");
                String arrivalPoint = rs.getString("arrival_point");
                Date departureDatetime = rs.getDate("departure_datetime");
                int estimatedTravelTime = rs.getInt("estimated_travel_time");
                int seatCapacity = rs.getInt("seat_capacity");
                int trainCarNumber = rs.getInt("train_car_number");
                BigDecimal seatPrice = rs.getBigDecimal("seat_price");
                String notes = rs.getString("notes");

                Schedules sc = new Schedules(scheduleId, providerId, requestId, tripCode, departurePoint, arrivalPoint, departureDatetime, estimatedTravelTime, seatCapacity, trainCarNumber, seatPrice, notes, "Pending");
                result.add(sc);
            }

            //Đóng cơ sở dữ liệu
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int insert(Schedules t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO schedules (schedule_id, provider_id, trip_code, departure_point, arrival_point, departure_datetime, estimated_travel_time, seat_capacity, seat_price, notes) "+
                    " VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getScheduleId());
            st.setString(2, t.getProviderId());
            st.setString(3, t.getTripCode());
            st.setString(4, t.getDeparturePoint());
            st.setString(5, t.getArrivalPoint());
            st.setDate(6, (Date) t.getDepartureDatetime());
            st.setInt(7, t.getEstimatedTravelTime());
            st.setInt(8,  t.getSeatCapacity());
            st.setBigDecimal(9,  t.getSeatPrice());
            st.setString(10,  t.getNotes());

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
    public int insertAll(ArrayList<Schedules> arr) {
        int count = 0;
        for (Schedules Schedules : arr) {
            count = count + this.insert(Schedules);
        }
        return count;
    }

    @Override
    public int delete(Schedules t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from schedules "+
                    " WHERE schedule_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getScheduleId());

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
    public int deleteAll(ArrayList<Schedules> arr) {
        int count = 0;
        for (Schedules Schedules : arr) {
            count = count + this.delete(Schedules);
        }
        return count;
    }

    @Override
    public int update(Schedules t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE schedules "+
                    " SET " +
                    " tripCode=?"+
                    ", departurePoint=?"+
                    ", arrivalPoint=?"+
                    ", departureDatetime=?"+
                    ", estimatedTravelTime=?"+
                    ", seatCapacity=?"+
                    ", seatPrice=?"+
                    ", notes=?"+
                    " WHERE schedule_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTripCode());
            st.setString(2, t.getDeparturePoint());
            st.setString(3, t.getArrivalPoint());
            st.setDate(4, (Date) t.getDepartureDatetime());
            st.setInt(5, t.getEstimatedTravelTime());
            st.setInt(6,  t.getSeatCapacity());
            st.setBigDecimal(7,  t.getSeatPrice());
            st.setString(8,  t.getNotes());
            st.setString(9, t.getScheduleId());


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


