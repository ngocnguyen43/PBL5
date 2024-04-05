package main.dao;

import main.model.Tickets;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class TicketsDAO implements DAOInterface<Tickets> {

    @Override
    public ArrayList<Tickets> selectAll() {
        ArrayList<Tickets> result = new ArrayList<Tickets>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM tickets";
            PreparedStatement st = con.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String ticketId = rs.getString("ticket_id");
                String scheduleId = rs.getString("schedule_id");
                int quantity = rs.getInt("quantity");
                BigDecimal price = rs.getBigDecimal("price");
                String status = rs.getString("status");
                String photo = rs.getString("photo");

                Tickets s = new Tickets(ticketId, scheduleId, quantity, price, status, photo);
                result.add(s);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Tickets selectById(Tickets t) {
        Tickets result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM tickets WHERE ticket_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTicketId());

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String ticketId = rs.getString("ticket_id");
                String scheduleId = rs.getString("schedule_id");
                int quantity = rs.getInt("quantity");
                BigDecimal price = rs.getBigDecimal("price");
                String status = rs.getString("status");
                String photo = rs.getString("photo");

                result = new Tickets(ticketId, scheduleId, quantity, price, status, photo);

                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int selectQuantity() {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT quantity FROM tickets ";
            PreparedStatement st = con.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                result = rs.getInt("quantity");

                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int selectQuantityById(String id) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT quantity FROM tickets WHERE ticket_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                result = rs.getInt("quantity");

                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public Tickets selectFromScheduleId(String id) {
        Tickets result = null;

        try {
            //Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();

            //Tạo ra đối tượng Statement 
            String sql = "SELECT * FROM tickets WHERE schedule_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);

            //Thực thi câu lệnh SQL 
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //Lấy thông tin 
            while(rs.next()) {
                String ticketId = rs.getString("ticket_id");
                String scheduleId = rs.getString("schedule_id");
                int quantity = rs.getInt("quantity");
                BigDecimal price = rs.getBigDecimal("price");
                String status = rs.getString("status");
                String photo = rs.getString("photo");

                result = new Tickets(ticketId, scheduleId, quantity, price, status, photo);

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
    public int insert(Tickets t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO tickets (ticket_id, schedule_id, quantity, price, status, photo) "+
                    " VALUES (?,?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTicketId());
            st.setString(2, t.getScheduleId());
            st.setInt(3, t.getQuantity());
            st.setBigDecimal(4, t.getPrice());
            st.setString(5, t.getStatus());
            st.setString(6, t.getPhoto());

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
    public int insertAll(ArrayList<Tickets> arr) {
        int count = 0;
        for (Tickets Tickets : arr) {
            count = count + this.insert(Tickets);
        }
        return count;
    }

    @Override
    public int delete(Tickets t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from tickets "+
                    " WHERE ticket_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTicketId());

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
    public int deleteAll(ArrayList<Tickets> arr) {
        int count = 0;
        for (Tickets Tickets : arr) {
            count = count + this.delete(Tickets);
        }
        return count;
    }

    @Override
    public int update(Tickets t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE Tickets "+
                    " SET " +
                    " quantity=?"+
                    ", price=?"+
                    ", status=?"+
                    ", photo=?"+
                    " WHERE ticket_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getQuantity());
            st.setBigDecimal(2, t.getPrice());
            st.setString(3, t.getStatus());
            st.setString(4, t.getPhoto());
            st.setString(5, t.getTicketId());

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


