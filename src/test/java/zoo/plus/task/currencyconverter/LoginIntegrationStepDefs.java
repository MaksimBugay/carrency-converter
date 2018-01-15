package zoo.plus.task.currencyconverter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginIntegrationStepDefs extends CurrencyConverterApplicationTests {

    static {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    private WebDriver browser = new ChromeDriver();

    @Given("^user on the login page$")
    public void i_am_on_the_login_page() throws Throwable {
        browser.get("http://localhost:8080/login");
    }

    @When("^user logged in using username as \"(.*)\" and password as \"(.*)\"$")
    public void i_fill_in_with(String username, String password) throws Throwable {
        browser.findElement(By.xpath("//*[@id='email']")).sendKeys(username);
        browser.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
        browser.findElement(By.xpath("//*[@id='signIn']")).click();
        browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Then("^user should be on the home page$")
    public void i_should_be_on_the_users_home_page() throws Throwable {
        Assert.assertEquals("http://localhost:8080/main/home", browser.getCurrentUrl());
        browser.close();
    }

}
