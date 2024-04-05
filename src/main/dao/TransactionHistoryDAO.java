package main.dao;

import main.model.TransactionHistory;

import java.sql.*;
import java.util.ArrayList;

public class TransactionHistoryDAO implements DAOInterface<TransactionHistory> {

    @Override
    public ArrayList<TransactionHistory> selectAll() {
        ArrayList<TransactionHistory> result = new ArrayList<TransactionHistory>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM transactionhistory";
            PreparedStatement st = con.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String transactionId = rs.getString("transaction_id");
                String userId = rs.getString("user_id");
                String ticketId = rs.getString("ticket_id");
                Date transactionDate = rs.getDate("transaction_date");
                int quantitySold = rs.getInt("quantity_sold");

                TransactionHistory s = new TransactionHistory(transactionId, userId, ticketId, transactionDate, quantitySold);
                result.add(s);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TransactionHistory selectById(TransactionHistory t) {
        TransactionHistory result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM transactionhistory WHERE transaction_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTransactionId());

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String transactionId = rs.getString("transaction_id");
                String userId = rs.getString("user_id");
                String ticketId = rs.getString("ticket_id");
                Date transactionDate = rs.getDate("transaction_date");
                int quantitySold = rs.getInt("quantity_sold");

                result = new TransactionHistory(transactionId, userId, ticketId, transactionDate, quantitySold);

                break;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public TransactionHistory selectfullname() {
//        TransactionHistory result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT transaction_id FROM transactionhistory ";
//            PreparedStatement st = con.prepareStatement(sql);
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//            while(rs.next()) {
//
//                String transactionId = rs.getString("transaction_id");
//                String userId = rs.getString("user_id");
//                String ticketId = rs.getString("ticket_id");
//                Date transactionDate = rs.getDate("transaction_date");
//                int quantitySold = rs.getInt("quantity_sold");
//
//                result = new TransactionHistory(transactionId, userId, ticketId, transactionDate, quantitySold);
//                break;
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    public TransactionHistory selectByUserId(String u_ID) {
        TransactionHistory result = null;

        try {
            //Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();

            //Tạo ra đối tượng Statement 
            String sql = "SELECT * FROM transactionhistory WHERE user_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, u_ID);

            //Thực thi câu lệnh SQL 
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //Lấy thông tin 
            while(rs.next()) {
                String transactionId = rs.getString("transaction_id");
                String userId = rs.getString("user_id");
                String ticketId = rs.getString("ticket_id");
                Date transactionDate = rs.getDate("transaction_date");
                int quantitySold = rs.getInt("quantity_sold");

                result = new TransactionHistory(transactionId, userId, ticketId, transactionDate, quantitySold);
                break;
            }

            //Đóng cơ sở dữ liệu
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public TransactionHistory selectByTicketId(String tk_ID) {
        TransactionHistory result = null;

        try {
            //Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();

            //Tạo ra đối tượng Statement
            String sql = "SELECT * FROM transactionhistory WHERE ticket_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tk_ID);

            //Thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //Lấy thông tin
            while(rs.next()) {
                String transactionId = rs.getString("transaction_id");
                String userId = rs.getString("user_id");
                String ticketId = rs.getString("ticket_id");
                Date transactionDate = rs.getDate("transaction_date");
                int quantitySold = rs.getInt("quantity_sold");

                result = new TransactionHistory(transactionId, userId, ticketId, transactionDate, quantitySold);
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
    public int insert(TransactionHistory t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO transactionhistory (transaction_id, user_id, ticket_id, transaction_date, quantity_sold) "+
                    " VALUES (?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTransactionId());
            st.setString(2, t.getUserId());
            st.setString(3, t.getTicketId());
            st.setDate(4, (Date) t.getTransactionDate());
            st.setInt(5, t.getQuantitySold());

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
    public int insertAll(ArrayList<TransactionHistory> arr) {
        int count = 0;
        for (TransactionHistory TransactionHistory : arr) {
            count = count + this.insert(TransactionHistory);
        }
        return count;
    }

    @Override
    public int delete(TransactionHistory t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from transactionhistory "+
                    " WHERE transaction_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTransactionId());

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
    public int deleteAll(ArrayList<TransactionHistory> arr) {
        int count = 0;
        for (TransactionHistory TransactionHistory : arr) {
            count = count + this.delete(TransactionHistory);
        }
        return count;
    }

    @Override
    public int update(TransactionHistory t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE transactionhistory "+
                    " SET " +
                    " transaction_date=?"+
                    ", quantity_sold=?"+
                    " WHERE transaction_id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setDate(1, (Date) t.getTransactionDate());
            st.setInt(2, t.getQuantitySold());
            st.setString(3, t.getTransactionId());


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


