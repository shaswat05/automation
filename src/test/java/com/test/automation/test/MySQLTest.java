package com.test.automation.test;

import com.test.automation.mysql.MySQLHelper;
import com.test.automation.mysql.MySQLHelperImpl;
import org.testng.annotations.Test;

/**
 * Test class to demonstrate the usage the MySQLHelper.
 */
public class MySQLTest {

    private final MySQLHelper mySQLHelper = new MySQLHelperImpl();

    @Test
    public void f() {
        mySQLHelper.select("SELECT * FROM user");
        mySQLHelper.execute("DELETE FROM user WHERE name='Alok'");
        mySQLHelper.select("SELECT * FROM user");
        mySQLHelper.execute("INSERT INTO user (name, password) VALUES ('Alok', 'Kumar')");
        mySQLHelper.select("SELECT * FROM user");
        mySQLHelper.execute("DELETE FROM user WHERE name='Alok'");
        mySQLHelper.execute("ALTER TABLE user AUTO_INCREMENT = 2;");
        mySQLHelper.select("SELECT * FROM user");
    }
}
