package com.test.automation.mysql;

public class MySQLFactory {

    public static IMySQL getMySQL() {
        return new MySQLJava();
    }

}
