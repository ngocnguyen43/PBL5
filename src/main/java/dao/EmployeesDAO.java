package dao;

public class EmployeesDAO  {

//    @Override
//    public ArrayList<Employees> selectAll() {
//        ArrayList<Employees> result = new ArrayList<Employees>();
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM employees";
//            PreparedStatement st = con.prepareStatement(sql);
//
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            while(rs.next()) {
//                String employeeId = rs.getString("employee_id");
//                String userId = rs.getString("user_id");
//                String fullName = rs.getString("full_name");
//                String gender = rs.getString("gender");
//                String email = rs.getString("email");
//                String phoneNumber = rs.getString("phone_number");
//                String position = rs.getString("position");
//                Date date_of_birth = rs.getDate("date_of_birth");
//                String photo = rs.getString("photo");
//
//                Employees s = new Employees(employeeId, userId, fullName, gender, email, phoneNumber, position, date_of_birth, photo);
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
//    public Employees selectById(Employees t) {
//        Employees result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM employees WHERE employee_id=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getEmployeeId());
//
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            while(rs.next()) {
//                String employeeId = rs.getString("employee_id");
//                String userId = rs.getString("user_id");
//                String fullName = rs.getString("full_name");
//                String gender = rs.getString("gender");
//                String email = rs.getString("email");
//                String phoneNumber = rs.getString("phone_number");
//                String position = rs.getString("position");
//                Date date_of_birth = rs.getDate("date_of_birth");
//                String photo = rs.getString("photo");
//
//                result = new Employees(employeeId, userId, fullName, gender, email, phoneNumber, position, date_of_birth, photo);
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
//    public Employees selectById(String id) {
//        Employees result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM employees WHERE employee_id=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, id);
//
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            while(rs.next()) {
//                String employeeId = rs.getString("employee_id");
//                String userId = rs.getString("user_id");
//                String fullName = rs.getString("full_name");
//                String gender = rs.getString("gender");
//                String email = rs.getString("email");
//                String phoneNumber = rs.getString("phone_number");
//                String position = rs.getString("position");
//                Date date_of_birth = rs.getDate("date_of_birth");
//                String photo = rs.getString("photo");
//
//                result = new Employees(employeeId, userId, fullName, gender, email, phoneNumber, position, date_of_birth, photo);
//                break;
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//    public Employees selectfullname() {
//        Employees result = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT full_name FROM employees ";
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
//                String photo = rs.getString("photo");
//
//                result = new Employees(employeeId, userId, fullName, gender, email, phoneNumber, position, date_of_birth, photo);
//                break;
//            }
//
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//    public Employees selectFromName(String name) {
//        Employees result = null;
//
//        try {
//            //Kết nối với CSDL
//            Connection con = JDBCUtil.getConnection();
//
//            //Tạo ra đối tượng Statement
//            String sql = "SELECT * FROM employees WHERE full_name = ?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, name);
//
//            //Thực thi câu lệnh SQL
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            //Lấy thông tin
//            while(rs.next()) {
//                String employeeId = rs.getString("employee_id");
//                String userId = rs.getString("user_id");
//                String fullName = rs.getString("full_name");
//                String gender = rs.getString("gender");
//                String email = rs.getString("email");
//                String phoneNumber = rs.getString("phone_number");
//                String position = rs.getString("position");
//                Date date_of_birth = rs.getDate("date_of_birth");
//                String photo = rs.getString("photo");
//
//                result = new Employees(employeeId, userId, fullName, gender, email, phoneNumber, position, date_of_birth, photo);
//                break;
//            }
//
//            //Đóng cơ sở dữ liệu
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    @Override
//    public int insert(Employees t) {
//        int result = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "INSERT INTO employees (employee_id, user_id, full_name, gender, email, phone_number, position, date_of_birth, photo) "+
//                    " VALUES (?,?,?,?,?,?,?,?,?)";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getEmployeeId());
//            st.setString(2, t.getUserId());
//            st.setString(3, t.getFullName());
//            st.setString(4, t.getGender());
//            st.setString(5, t.getEmail());
//            st.setString(6, t.getPhoneNumber());
//            st.setString(7, t.getPosition());
//            st.setDate(8, (Date) t.getDateOfBirth());
//            st.setString(9, t.getPhoto());
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
//    public int insertAll(ArrayList<Employees> arr) {
//        int count = 0;
//        for (Employees Employees : arr) {
//            count = count + this.insert(Employees);
//        }
//        return count;
//    }
//
//    @Override
//    public int delete(Employees t) {
//        int result = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "DELETE from employees "+
//                    " WHERE employee_id=?";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getEmployeeId());
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
//    public int deleteAll(ArrayList<Employees> arr) {
//        int count = 0;
//        for (Employees Employees : arr) {
//            count = count + this.delete(Employees);
//        }
//        return count;
//    }
//
//    @Override
//    public int update(Employees t) {
//        int result = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "UPDATE employees "+
//                    " SET " +
//                    " full_name=?"+
//                    ", gender=?"+
//                    ", email=?"+
//                    ", phone_number=?"+
//                    ", position=?"+
//                    ", date_of_birth=?"+
//                    " WHERE employee_id=?";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getFullName());
//            st.setString(2, t.getGender());
//            st.setString(3, t.getEmail());
//            st.setString(4, t.getPhoneNumber());
//            st.setString(5, t.getPosition());
//            st.setDate(6, (Date) t.getDateOfBirth());
//            st.setString(7, t.getEmployeeId());
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


