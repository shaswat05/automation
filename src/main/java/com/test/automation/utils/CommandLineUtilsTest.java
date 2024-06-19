package com.test.automation.utils;

import com.test.automation.logger.ILogger;
import com.test.automation.logger.LoggerFactory;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineUtilsTest {

    private static ILogger logger = LoggerFactory.getLogger("CommandLineUtils");

    public static void run(String dir, String command) {
        String[] commandArr = command.split(" ");
        ProcessBuilder builder = new ProcessBuilder(commandArr);
        System.out.println(dir);
        logger.log("Command: " + String.join(" ", builder.command().toArray(new String[0])));
        builder.directory(new File(dir));
        try {
            builder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
