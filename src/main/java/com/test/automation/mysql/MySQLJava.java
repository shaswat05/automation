package com.test.automation.mysql;

import com.test.automation.allure.AllureReportUtils;
import com.test.automation.exceptions.SQLRuntimeException;
import com.test.automation.logger.ILogger;
import com.test.automation.logger.LoggerFactory;
import com.test.automation.utils.PropertiesLoader;

import java.sql.*;
import java.util.*;

class MySQLJava implements IMySQL {

    private final ILogger logger = LoggerFactory.getLogger(MySQLJava.class.getSimpleName());

    /**
     * This method is used to execute SELECT query only.
     *
     * @param sql SQL query to get the results.
     * @return Returns the result as List<Map<String, Object>>
     */
    @Override
    public List<Map<String, Object>> select(String sql) {
        log(sql, "[Select Query]");
        try {
            List<Map<String, Object>> responseMap = map(getStatement().executeQuery(sql));
            log(responseMap, "[Select Query Response]");
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
        log(sql, "[Execute Query]");
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
        Properties properties = PropertiesLoader.getProperties("mysql.properties");
        String url = properties.getProperty("mysql.db.host");
        String driverClassName = properties.getProperty("mysql.db.driver.class.name");
        String user = properties.getProperty("mysql.db.user");
        String password = properties.getProperty("mysql.db.password");
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
        List<Map<String, Object>> results = new ArrayList<>();
        try {
            if (rs != null) {
                ResultSetMetaData meta = rs.getMetaData();
                int numColumns = meta.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
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

    private void log(Object sql, Object logTag) {
        String lt = "[MySQL][" + "dbName:automation" + "]" + logTag + " ";
        logger.log(lt + sql);
        AllureReportUtils.addAttachment(lt, sql.toString());
    }

}
