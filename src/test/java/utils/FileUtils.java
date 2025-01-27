package utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
public class FileUtils {

	
	 public static void saveToPropertiesFile(String fileName, String key, String value) {
	        Properties prop = new Properties();
	        
	        try {
	            // Load existing properties file if it exists
	            FileInputStream fis = new FileInputStream(fileName);
	            prop.load(fis);  // Load existing properties
	            fis.close();
	        } catch (IOException e) {
	            // File might not exist, that's okay, we will create it
	            System.out.println("Properties file not found, creating a new one.");
	        }
	        
	        try {
	            // Add new case number to the properties
	            prop.setProperty(key, value);
	            
	            // Write the updated properties back to the file
	            FileOutputStream fos = new FileOutputStream(fileName);
	            prop.store(fos, null); // Save all the properties
	            fos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
