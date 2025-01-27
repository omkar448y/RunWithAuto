package utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ScreenshotHelper {
	  private WebDriver driver;

	    public ScreenshotHelper(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void takeScreenshot(Scenario scenario) {
	        if (scenario.isFailed()) {
	            TakesScreenshot screenshot = (TakesScreenshot) driver;
	            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

	            // Generate a unique filename
	            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	            String sanitizedScenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9_-]", "_");
	            String fileName = "C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\screenshots\\" + sanitizedScenarioName + "_" + timestamp + ".png";
	            File destFile = new File(fileName);

	            try {
	                // Ensure the directory exists
	                Files.createDirectories(Paths.get("C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\screenshots"));

	                // Copy the screenshot file to the destination
	                Files.copy(srcFile.toPath(), destFile.toPath());
	                System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
