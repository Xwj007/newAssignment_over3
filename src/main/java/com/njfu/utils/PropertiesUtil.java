package com.njfu.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    public static String getProperty(String key){
        return PropertiesConfigure.getProperty(key);
    }

    private static class PropertiesConfigure{
        private static Properties properties = null;

        static {
            try{
                Resource resource =  new ClassPathResource("messages/message.properties");
                properties = PropertiesLoaderUtils.loadProperties(resource);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        public static String getProperty(String key){
            return properties.getProperty(key);

        }
    }
}
