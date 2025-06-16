package selenium_page_factory.pages;

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

    public void fillEmail(String email) {
        wait.until(d -> loginObject.inputEmail.isDisplayed());
        loginObject.inputEmail.sendKeys(email);
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
