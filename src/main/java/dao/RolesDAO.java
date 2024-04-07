package dao;

public class RolesDAO  {

//    @Override
//    public ArrayList<Roles> selectAll() {
//        ArrayList<Roles> result = new ArrayList<Roles>();
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM roles";
//            PreparedStatement st = con.prepareStatement(sql);
//
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            while(rs.next()) {
//                String roleId = rs.getString("role_id");
//                String roleName = rs.getString("role_name");
//
//                Roles s = new Roles(roleId, roleName);
//                result.add(s);
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public Roles selectById(Roles t) {
//        Roles result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM roles WHERE role_id=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getRoleId());
//
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            while(rs.next()) {
//                String roleId = rs.getString("role_id");
//                String roleName = rs.getString("role_name");
//
//                result = new Roles(roleId, roleName);
//                break;
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public Roles selectByName(Roles t) {
//        Roles result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM roles WHERE role_name=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getRoleName());
//
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            while(rs.next()) {
//                String roleId = rs.getString("role_id");
//                String roleName = rs.getString("role_name");
//
//                result = new Roles(roleId, roleName);
//                break;
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public ArrayList<String> selectRoleName() {
//        ArrayList<String> result = new ArrayList<String>();
//        String roleName = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT role_name FROM roles ";
//            PreparedStatement st = con.prepareStatement(sql);
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//            while(rs.next()) {
//                roleName = rs.getString("role_name");
//                result.add(roleName);
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public int insert(Roles t) {
//        int result = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "INSERT INTO roles (role_id, role_name) "+
//                    " VALUES (?,?)";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getRoleId());
//            st.setString(2, t.getRoleName());
//
//            result = st.executeUpdate();
//
//            System.out.println("Bạn đã thực thi: "+ sql);
//            System.out.println("Có "+ result+" dòng bị thay đổi!");
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    @Override
//    public int insertAll(ArrayList<Roles> arr) {
//        int count = 0;
//        for (Roles Roles : arr) {
//            count = count + this.insert(Roles);
//        }
//        return count;
//    }
//
//    @Override
//    public int delete(Roles t) {
//        int result = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "DELETE from roles "+
//                    " WHERE role_id=?";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getRoleId());
//
//            System.out.println(sql);
//            result = st.executeUpdate();
//
//            System.out.println("Bạn đã thực thi: "+ sql);
//            System.out.println("Có "+ result+" dòng bị thay đổi!");
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    @Override
//    public int deleteAll(ArrayList<Roles> arr) {
//        int count = 0;
//        for (Roles Roles : arr) {
//            count = count + this.delete(Roles);
//        }
//        return count;
//    }
//
//    @Override
//    public int update(Roles t) {
//        int result = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//
////            UPDATE Roles SET booking_date='2024-02-21 10:08:00', status='Confirmed' WHERE booking_id=80001;
//
//            String sql = "UPDATE roles "+
//                    " SET " +
//                    " role_name=?"+
//                    " WHERE role_id=?";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getRoleName());
//            st.setString(2, t.getRoleId());
//
//
//            System.out.println(sql);
//            result = st.executeUpdate();
//
//            System.out.println("Bạn đã thực thi: "+ sql);
//            System.out.println("Có "+ result+" dòng bị thay đổi!");
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
}




