package com.test.automation.mysql;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLHelperImpl implements MySQLHelper {

    private final Logger log = LogManager.getLogger(MySQLHelperImpl.class.getName());

    /**
     * This method is used to execute SELECT query only.
     *
     * @param sql SQL query to get the results.
     * @return Returns the result as List<Map<String, Object>>
     */
    @Override
    public List<Map<String, Object>> select(String sql) {
        log("[Select Query] " + sql);
        try {
            List<Map<String, Object>> responseMap = map(getStatement().executeQuery(sql));
            log("[Select Query Response] " + responseMap);
            return responseMap;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    /**
     * This method is used to execute CREATE, ALTER, INSERT & UPDATE queries.
     * SELECT query should not be used with this method.
     *
     * @param sql SQL query to be executed.
     * @return Returns the number of rows affected.
     */
    @Override
    public int execute(String sql) {
        log("[Execute Query] " + sql);
        try {
            return getStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    private Connection createConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/automation";
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "root";
        return createConnection(driverClassName, url, user, password);
    }

    private Connection createConnection(String driverClassName, String url, String user, String password) throws SQLException {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(url, user, password);
    }

    private Statement getStatement() throws SQLException {
        return createConnection().createStatement();
    }

    private List<Map<String, Object>> map(ResultSet rs) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        try {
            if (rs != null) {
                ResultSetMetaData meta = rs.getMetaData();
                int numColumns = meta.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<String, Object>();
                    for (int i = 1; i <= numColumns; ++i) {
                        String name = meta.getColumnName(i);
                        Object value = rs.getObject(i);
                        row.put(name, value);
                    }
                    results.add(row);
                }
            }
        } finally {
            close(rs);
        }
        return results;
    }

    private void log(String str) {
        log.info("[MySQL]" + str);
    }

}
