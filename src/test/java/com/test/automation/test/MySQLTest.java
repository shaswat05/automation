package com.test.automation.test;

import com.test.automation.allure.AllureReportUtils;
import com.test.automation.mysql.IMySQL;
import com.test.automation.mysql.MySQLFactory;
import org.testng.annotations.Test;

/**
 * Test class to demonstrate the usage the MySQLHelper.
 */
public class MySQLTest {

    private final IMySQL mysql = MySQLFactory.getMySQL();

    @Test
    public void f() {
        AllureReportUtils.updateTestMethodNameAndDescription("MySQL test with AllureReportUtils");
        mysql.select("SELECT * FROM user");
        mysql.execute("DELETE FROM user WHERE name='Alok'");
        mysql.select("SELECT * FROM user");
        mysql.execute("INSERT INTO user (name, password) VALUES ('Alok', 'Kumar')");
        mysql.select("SELECT * FROM user");
        mysql.execute("DELETE FROM user WHERE name='Alok'");
        mysql.execute("ALTER TABLE user AUTO_INCREMENT = 2;");
        mysql.select("SELECT * FROM user");
    }
}
