package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LocatorSuccessLoginScenario {
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
    public void clickForgotPassword() throws Exception {
        Thread.sleep(Duration.ofSeconds(1));
        WebElement linkForgotPassword = webDriver.findElement(
                By.xpath("//div[@class='forgot-pwd-container']//child::a[contains(text(), 'Forgot your password?')]"));
        linkForgotPassword.click();
        Thread.sleep(Duration.ofSeconds(2));
    }

    @Test(dependsOnMethods = "clickForgotPassword")
    public void fillAllFields() throws Exception {
        Thread.sleep(Duration.ofSeconds(1));
        List<WebElement> fields = webDriver.findElements(
                By.xpath("//input[@placeholder='Phone Number']//preceding-sibling::input"));
        for (WebElement webElement : fields) {
            Thread.sleep(Duration.ofSeconds(1));
            if (webElement.getAttribute("placeholder").equals("Name")) {
                webElement.sendKeys("Test");
            }
            if (webElement.getAttribute("placeholder").equals("Email")) {
                webElement.sendKeys("test@test.com");
            }
        }
    }

    @Test(dependsOnMethods = "fillAllFields")
    public void clickButton() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        List<WebElement> buttonResetLogins = webDriver
                .findElements(By.xpath("//div[@class='forgot-pwd-btn-conainer']//descendant::button"));
        buttonResetLogins.get(1).click();
    }

    @Test(dependsOnMethods = "clickButton")
    public void getPasswordAndTryLogin() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        WebElement textInfoMsg = webDriver
                .findElement(By.xpath("//div[@id='container']//following::p[@class='infoMsg']"));
        System.out.println(textInfoMsg.getText());
        String password = extractPassword(textInfoMsg.getText());

        // back to login
        Thread.sleep(Duration.ofSeconds(2));
        List<WebElement> buttonResetLogins = webDriver
                .findElements(By.xpath("//div[@class='forgot-pwd-btn-conainer']//descendant::button"));
        buttonResetLogins.get(0).click();

        // login
        Thread.sleep(Duration.ofSeconds(2));
        // loader
        WebElement inputUsername = webDriver.findElement(By.cssSelector("input#inputUsername"));

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(t -> ExpectedConditions.visibilityOf(inputUsername));

        inputUsername.sendKeys("test@test.com");

        Thread.sleep(Duration.ofSeconds(2));
        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@name='inputPassword']"));
        inputPassword.sendKeys(password);

        Thread.sleep(Duration.ofSeconds(2));
        WebElement buttonSignIn = webDriver
                .findElement(By.xpath("//button[contains(@class,'signInBtn') and contains(text(), 'Sign In')]"));
        buttonSignIn.click();

        // login-container
        Thread.sleep(Duration.ofSeconds(2));
        List<WebElement> loginContainer = webDriver.findElements(By.xpath("//div[@class='login-container']//child::*"));
        for (WebElement webElement : loginContainer) {
            System.out.println(webElement.getText());
        }
    }

    public String extractPassword(String infoText) {
        String targetString = "";

        // Define the known parts around the target string
        String prefix = "'";
        String suffix = "'";

        // Find the starting and ending indices
        int startIndex = infoText.indexOf(prefix) + prefix.length();
        int endIndex = infoText.indexOf(suffix, startIndex); // Start searching for suffix from startIndex

        // Check if both prefix and suffix were found
        if (startIndex != -1 && endIndex != -1) {
            targetString = infoText.substring(startIndex, endIndex);
        }

        return targetString;
    }

    @AfterSuite
    public void closeBrowser() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        webDriver.close();
    }
}
