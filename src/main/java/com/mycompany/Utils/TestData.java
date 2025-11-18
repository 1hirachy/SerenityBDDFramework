package com.mycompany.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestData {

    private static final Properties prop = new Properties();

    static {
        try {
            prop.load(new FileInputStream("src/main/resources/testdata.properties"));
        } catch (IOException e){
            throw new RuntimeException("Failed to load testdata.properties");
        }
    }

    public static String get(String key){
        return prop.getProperty(key);
    }
}
