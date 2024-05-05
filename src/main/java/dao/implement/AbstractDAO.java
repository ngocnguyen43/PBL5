package dao.implement;

import config.EnvConfig;
import dao.interfaces.DAOInterface;
import utils.mapper.interfaces.IMapper;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDAO<T> implements DAOInterface<T> {


    private final Logger logger = Logger.getLogger(AbstractDAO.class.getName());

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + EnvConfig.load().get("DB_URL") + "/" + EnvConfig.load().get("DB_NAME");
            return DriverManager.getConnection(url, EnvConfig.load().get("USER_NAME"), EnvConfig.load().get("PASSWORD"));
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    @Override
    public <T> List<T> query(String sql, IMapper<T> mapper, Object... parameters) {
        List<T> list = new ArrayList<T>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = getConnection();
            statement = connection.prepareCall(sql);
            // set parameter
            setParams(statement, parameters);
            System.out.println(statement);
            result = statement.executeQuery();
            while (result.next()) {
                list.add(mapper.mapRow(result));
            }
            return list;
        } catch (SQLException e) {
            // TODO: handle exception
            logger.log(Level.SEVERE, e.getMessage());

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
                logger.log(Level.WARNING, e2.getMessage());
            }
        }
        return null;
    }

    private void setParams(PreparedStatement statement, Object... parameters) {
        try {

            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long) {
                    statement.setLong(index, (Long) parameter);
                } else if (parameter instanceof String) {
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    statement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Timestamp) {
                    statement.setTimestamp(index, (Timestamp) parameter);
                } else if (parameter instanceof Float) {
                    statement.setFloat(index, (float) parameter);
                } else if (parameter instanceof BigDecimal) {
                    statement.setBigDecimal(index, (BigDecimal) parameter);
                } else {
                    statement.setNull(index, Types.NULL);
                }

            }
        } catch (SQLException e) {
            // TODO: handle exception
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void setParams(PreparedStatement statement, int pos, Object... parameters) {
        try {

            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = pos + i + 1;
                if (parameter instanceof Long) {
                    statement.setLong(index, (Long) parameter);
                } else if (parameter instanceof String) {
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    statement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Timestamp) {
                    statement.setTimestamp(index, (Timestamp) parameter);
                } else if (parameter instanceof Float) {
                    statement.setFloat(index, (float) parameter);
                } else if (parameter instanceof BigDecimal) {
                    statement.setBigDecimal(index, (BigDecimal) parameter);
                } else {
                    statement.setNull(index, Types.NULL);
                }

            }
        } catch (SQLException e) {
            // TODO: handle exception
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void update(String sql, Object... params) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParams(statement, params);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    @Override
    public void insert(String sql, Object... params) throws SQLException {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParams(statement, params);
            statement.executeUpdate();
            connection.commit();
//			return params[0];

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParams(statement, params);
            // In câu lệnh SQL trước khi thực thi
//            System.out.println("Executing SQL: " + statement.toString());
            statement.executeUpdate();
            connection.commit();
//			return params[0];

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void bulkCreate(String sql, List<Object[]> paramsList) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);

            // Iterate through the list of parameters and add batches
            for (Object[] params : paramsList) {
                setParams(statement, params);
                statement.addBatch();
            }

            // Execute batch
            statement.executeBatch();

            // Commit transaction
            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (Exception e2) {
                    logger.log(Level.WARNING, "Error rolling back transaction", e2);
                }
            }
            logger.log(Level.SEVERE, "Error executing bulk create", e);
            throw e;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e2) {
                logger.log(Level.WARNING, "Error closing connection or statement", e2);
            }
        }
    }

    @Override
    public <T> List<T> query(String sql, IMapper<T> mapper, List<Object[]> paramsList) {
        List<T> list = new ArrayList<T>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);

            // Iterate through the list of parameters and add batches
            int pos = 0;
            for (Object[] params : paramsList) {
                setParams(statement, pos, params);
                pos += params.length;
            }
            System.out.println(statement.toString());
            // Execute batch
            result = statement.executeQuery();
            while (result.next()) {
                list.add(mapper.mapRow(result));
            }
            return list;


        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getMessage()); // Handle or log the exception as needed
        } finally {
            // Close resources
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, e.getMessage()); // Handle or log the exception as needed
            }
        }
        return null;
    }

    @Override
    public void bulkCreate(List<String> sqls, List<List<Object[]>> listParams) throws SQLException {
        if (listParams.isEmpty()) throw new RuntimeException();
        if (sqls.size() != listParams.size()) throw new RuntimeException();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            for (var index = 0; index < listParams.size(); index++) {
                var sql = sqls.get(index);
                var params = listParams.get(index);
                statement = connection.prepareStatement(sql);

                for (Object[] param : params) {
                    statement.clearParameters();
                    setParams(statement, param);
                    statement.addBatch();

                }

                assert statement != null;
                statement.executeBatch();

            }
            // Execute batch

            // Commit transaction
            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (Exception e2) {
                    logger.log(Level.WARNING, "Error rolling back transaction", e2);
                }
            }
            logger.log(Level.SEVERE, "Error executing bulk create", e);
            throw e;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e2) {
                logger.log(Level.WARNING, "Error closing connection or statement", e2);
            }
        }
    }

}
