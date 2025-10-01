package Stepdefinitions;

import Pages.Loginpage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginSteps {

    WebDriver driver;
    Loginpage loginPage;

    public static void timeout2000(){
        try{
            Thread.sleep(2000);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static void timeout3000(){
        try{
            Thread.sleep(3000);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    @BeforeClass
    @Given("user is on the login page")
    public void user_is_on_the_login_page(){
        driver = new ChromeDriver();
        driver.get("https://internal.meris.com.au/");
        driver.manage().window().maximize();
        loginPage =  new Loginpage(driver);

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://internal.meris.com.au/";

        Assert.assertEquals(actualURL, expectedURL, "❌ URL does not match!");

        System.out.println("✅ URL verification passed!");
    }

    @Test(priority = 1)
    @When("user enter valid username and password")
    public void user_enter_valid_username_and_password(){
        LoginSteps.timeout3000();
        loginPage.enterUsername("leo@iitcglobal.com");
        LoginSteps.timeout2000();
        loginPage.enterPassword("123");
        LoginSteps.timeout2000();
    }

    @Test(priority = 2)
    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password(){
        loginPage.enterUsername("sdfdsf");
        LoginSteps.timeout2000();
        loginPage.enterPassword("123");
        LoginSteps.timeout2000();
    }

    @Test(priority = 3)
    @And("clicks the login button")
    public void clicks_the_login_button(){
        loginPage.clickLoginBtn();
        LoginSteps.timeout2000();
    }

    @AfterMethod
    @Then("user should be redirect to the dashboard")
    public void user_should_be_redirect_to_the_dashboard(){
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://internal.meris.com.au/");
    }

    @AfterTest
    @Then("an error message is displayed")
    public void an_error_message_is_displayed(){
        Assert.assertEquals(loginPage.getErrormessage(), "invalid credentials");
    }


}
