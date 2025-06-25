package selenium_page_factory.pages;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.LoginObject;

public class LoginPage extends BasePage {
    public LoginObject loginObject;

    public LoginPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.loginObject = new LoginObject(webDriver);
    }

    public void fillEmail(String email) throws IOException {
        wait.until(d -> loginObject.inputEmail.isDisplayed());
        loginObject.inputEmail.sendKeys(email);
        TakesScreenshot ts = (TakesScreenshot) webDriver;
        File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
        String currentWorkingDirectory = System.getProperty("user.dir");

        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        // Define the format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format the current date and time
        String formattedNow = now.format(formatter);

        FileUtils.copyFile(screenshotFile,
                new File(currentWorkingDirectory + "/sc/screenshot-" + formattedNow + ".png"));
    }

    public void fillPassword(String password) {
        wait.until(d -> loginObject.inputPassword.isDisplayed());
        loginObject.inputPassword.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(d -> loginObject.buttonLogin.isDisplayed());
        loginObject.buttonLogin.click();
    }
}
