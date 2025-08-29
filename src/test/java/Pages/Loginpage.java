package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {

    WebDriver driver;

    public Loginpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    WebElement useremail;

    @FindBy(id ="password")
    WebElement pass;

    @FindBy(xpath="//*[@id=\"formAuthentication\"]/div[3]/button")
    WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"formAuthentication\"]/div[2]/span/div/span")
    WebElement errormessage;

    public static void timeout2000() {
        try {
            Thread.sleep(2000);
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void enterUsername(String username){
        useremail.clear();
        useremail.sendKeys(username);
        Loginpage.timeout2000();
    }

    public void enterPassword(String password){
        pass.clear();
        pass.sendKeys(password);
        Loginpage.timeout2000();
    }

    public void clickLoginBtn(){
        loginBtn.click();
        Loginpage.timeout2000();
    }

    public String getErrormessage(){
        return errormessage.getText();
    }


}
