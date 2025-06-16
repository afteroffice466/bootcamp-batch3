package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class LoginObject extends BaseObject {
    @FindBy(xpath = "//input[@id='userEmail']")
    public WebElement inputEmail;

    @FindBy(xpath = "//input[@id='userPassword']")
    public WebElement inputPassword;

    @FindBy(xpath = "//input[@id='login']")
    public WebElement buttonLogin;

    public LoginObject(WebDriver webDriver) {
        super(webDriver);

        PageFactory.initElements(webDriver, this);
    }
}
