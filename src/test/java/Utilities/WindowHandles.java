package Utilities;
import org.openqa.selenium.WebDriver;
import java.util.Set;
public class WindowHandles {

    // Method to switch to the child window 
    public static void switchToChildWindow(WebDriver driver) throws InterruptedException {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance is null");
        }

        String mainWindowHandle = driver.getWindowHandle();
        System.out.println("Main Window Handle: " + mainWindowHandle);

        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println("All Window Handles: " + windowHandles);

        // Loop through the handles and switch to the new window 
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                System.out.println("Switched to Child Window Handle: " + handle);
                break;
            }
        }
        System.out.println("Title of the new child window: " + driver.getTitle());
    }
}
