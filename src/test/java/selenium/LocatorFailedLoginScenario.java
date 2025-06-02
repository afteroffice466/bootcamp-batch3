package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LocatorFailedLoginScenario {
    public WebDriver webDriver;

    @BeforeSuite
    public void startBrowser() {
        System.out.println("Browser Start...");
        System.setProperty("webdriver.chrome.driver",
                "/Users/filla/bootcamp-batch3/chromedriver-mac-arm64/chromedriver");

        webDriver = new ChromeDriver();

        webDriver.get("https://rahulshettyacademy.com/locatorspractice/");
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void fillTheUsername() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        // WebElement inputUsername = webDriver.findElement(By.id("inputUsername"));
        WebElement inputUsername = webDriver.findElement(By.cssSelector("input#inputUsername"));
        inputUsername.sendKeys("test@test.com");
    }

    @Test(dependsOnMethods = "fillTheUsername")
    public void fillThePassword() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        WebElement inputUsername = webDriver.findElement(By.xpath("//input[@name='inputPassword']"));
        inputUsername.sendKeys("inipassword");
    }

    @Test(dependsOnMethods = "fillThePassword")
    public void clickButton() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        WebElement buttonSignIn = webDriver
                .findElement(By.xpath("//button[contains(@class,'signInBtn') and contains(text(), 'Sign In')]"));
        buttonSignIn.click();
    }

    @Test(dependsOnMethods = "clickButton")
    public void getError() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        WebElement errorText = webDriver
                .findElement(By.xpath("//form[@class='form']//child::p[@class='error']"));
        System.out.println("ErrorText: " + errorText.getText());
        Assert.assertEquals("* Incorrect username or password", errorText.getText());
    }

    @AfterSuite
    public void closeBrowser() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        webDriver.close();
    }
}
