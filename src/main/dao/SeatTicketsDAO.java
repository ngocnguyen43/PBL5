package main.dao;

import main.dao.interfaces.DAOInterface;
import main.model.SeatTickets;

import java.sql.*;
import java.util.ArrayList;

public class SeatTicketsDAO implements DAOInterface<SeatTickets> {

    @Override
    public ArrayList<SeatTickets> selectAll() {
        ArrayList<SeatTickets> result = new ArrayList<SeatTickets>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM seattickets";
            PreparedStatement st = con.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String seatId = rs.getString("seat_id");
                String ticketId = rs.getString("ticket_id");

                SeatTickets s = new SeatTickets(seatId, ticketId);
                result.add(s);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SeatTickets selectById(SeatTickets t) {
        SeatTickets result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM seattickets WHERE ticket_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTicketId());

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String seatId = rs.getString("seat_id");
                String ticketId = rs.getString("ticket_id");

                result = new SeatTickets(seatId, ticketId);
                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(SeatTickets t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO seattickets (seat_it, ticket_id) "+
                    " VALUES (?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getSeatId());
            st.setString(2, t.getTicketId());

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
    public int insertAll(ArrayList<SeatTickets> arr) {
        int count = 0;
        for (SeatTickets SeatTickets : arr) {
            count = count + this.insert(SeatTickets);
        }
        return count;
    }

    @Override
    public int delete(SeatTickets t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from seattickets "+
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
    public int deleteAll(ArrayList<SeatTickets> arr) {
        int count = 0;
        for (SeatTickets SeatTickets : arr) {
            count = count + this.delete(SeatTickets);
        }
        return count;
    }

    @Override
    public int update(SeatTickets t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE seattickets "+
                    " SET " +
                    " ticket_id=?"+
                    " WHERE seat_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getSeatId());
            st.setString(2, t.getTicketId());


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



