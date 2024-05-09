package com.test.automation.mysql;

import java.util.List;
import java.util.Map;

public interface MySQLHelper {

    /**
     * This method is used to execute SELECT query only.
     *
     * @param sql SQL query to get the results.
     * @return Returns the result as List<Map<String, Object>>
     */
    public List<Map<String, Object>> select(String sql);

    /**
     * This method is used to execute CREATE, ALTER, INSERT & UPDATE queries.
     * SELECT query should not be used with this method.
     *
     * @param sql SQL query to be executed.
     * @return Returns the number of rows affected.
     */
    public int execute(String sql);

}
