package kz.epam.mrymbayev.pm;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class PropertyManager {
    private static final Logger log = Logger.getLogger(PropertyManager.class);
    private static PropertyManager instance;
    private Properties properties;

    private PropertyManager() {
    }

    public static PropertyManager getInstance(){
        if(instance == null){
            instance = new PropertyManager();
        }
        return instance;
    }

    public void loadProperties(String fileName){
        try(InputStream is = PropertyManager.class.getClassLoader().getResourceAsStream(fileName)) {
            properties = new Properties();
            properties.load(is);
            log.trace("Property file: " + fileName + " was loaded successfully.");
        } catch (IOException e) {
            log.error("Error, unable to load: " + fileName + " property file.");
            throw new PropertyManagerException();
        }
    }

    public String getProperty(String key){
        if(properties == null){
            log.fatal("Property file wasn't load. Please load property file by the loadProperties(String fileName) method.");
            throw new PropertyManagerException("Property file wasn't load, " +
                    "please load property file by the loadProperties(String fileName) method.");
        }
        final String value = properties.getProperty(key);
        if(value == null){
            return null;
        }
        try{
            return new String(value.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.fatal("Encoding of property file not supported. ");
            throw new PropertyManagerException("Encoding of property file not supported. ");
        }
    }




}
