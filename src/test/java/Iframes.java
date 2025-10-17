import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Iframes {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("URL");
        driver.manage().window().maximize();
    }

    @Test
    public void test_with_iframe() {
        //By finding all the web elements using iframe tag
        List<WebElement> iframeList = driver.findElements(By.tagName("iframeResult"));
        System.out.println("Total number of iframes are: "+iframeList.size());

        driver.switchTo().frame(0); //Switch by Index
        driver.switchTo().frame("iframeResult"); //Switch to by frame name

        //Perform all the required tasks in the frame 0
        //Switching back to the main window
        driver.switchTo().defaultContent();

        //By executing a java script
        JavascriptExecutor execute = (JavascriptExecutor) driver;
        Integer noOfframes = Integer.parseInt(execute.executeScript("Return window.length()").toString());
        System.out.println("Number of iframes on the page are: "+noOfframes);
    }

    @Test
    public void test_with_alerts() {

        Alert alert = driver.switchTo().alert(); //Switch to alert
        String alertmessage = driver.switchTo().alert().getText(); //capture alert message
        System.out.println(alertmessage);
        try {
            Thread.sleep(5000);
        }catch (Exception ex){}

        alert.accept(); //Click OK button from alert

        alert.dismiss(); //Click cancel button from alert

        alert.sendKeys("message"); //Send data to alert box
    }

    @Test
    public void test_handle_windows() {

        // It will return the parent window name as a String
        String parentWindow = driver.getWindowHandle();

        // It will return list of windows name as a String
        Set<String> listofwindows = driver.getWindowHandles();

        // Now iterate using Iterator
        Iterator<String> iterate = listofwindows.iterator();
        while (iterate.hasNext()){
            String childWindow = iterate.next();

            if(!parentWindow.equals(childWindow)){
                driver.switchTo().window(childWindow);
                System.out.println(driver.switchTo().window(childWindow).getTitle());

                driver.close();
            }
        }
        driver.switchTo().window(parentWindow); //switch to the parent window
    }
}
