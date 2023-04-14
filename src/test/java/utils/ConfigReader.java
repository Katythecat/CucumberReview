package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties properties;
    public static Properties readProperties() {
        try {
            FileInputStream file = new FileInputStream(Constants.PROPERTY_FILE_PATH);
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static String getValueOfProp(String propertyKey){

        return properties.getProperty(propertyKey);
    }

}
