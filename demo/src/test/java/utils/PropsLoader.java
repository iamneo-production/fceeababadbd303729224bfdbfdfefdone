package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsLoader {
    
    private Properties properties;

    private PropsLoader(String filePath) {
        properties = new Properties();
        try (InputStream inputStream = new FileInputStream(System.getProperty("user.dir")+filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropsLoader getInstance(String filePath) {
        return new PropsLoader(filePath);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
