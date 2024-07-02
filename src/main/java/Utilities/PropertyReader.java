package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static Properties properties;

    static {
        try {
            // Load the properties file
            FileInputStream inputStream = new FileInputStream("src/main/java/Config/Config.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Config.properties not found at src/main/java/Config/Config.properties");
        }
    }

    public String getProperty(String key) {
        // Return the property value for the given key
        return properties.getProperty(key);
    }
    public void loadProperties() {
        // Your logic to load properties, e.g., from a properties file
        try {
            // Load the properties file
            FileInputStream inputStream = new FileInputStream("src/main/java/Config/Config.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Config.properties not found at src/main/java/Config/Config.properties");
        }
    }
}
