package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class CheckoutObject extends BaseObject {
    @FindBy(xpath = "//div[contains(text(), 'CVV Code')]//following-sibling::input")
    public WebElement cvvInput;

    @FindBy(xpath = "//div[contains(text(), 'Name on Card')]//following-sibling::input")
    public WebElement nameOnCard;

    @FindBy(xpath = "//input[contains(@placeholder, 'Select Country')]")
    public WebElement selectCountry;

    @FindBy(xpath = "//a[contains(text(), 'Place Order')]")
    public WebElement buttonPlaceOrder;

    @FindBy(xpath = "//input[contains(@placeholder, 'Select Country')]//following-sibling::section")
    public WebElement sectionRecomendationCountry;

    public CheckoutObject(WebDriver webDriver) {
        super(webDriver);

        PageFactory.initElements(webDriver, this);
    }
}
