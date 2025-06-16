package selenium_page_factory.object_repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class ProductDisplayObject extends BaseObject {
    @FindBy(xpath = "//button[contains(text(), 'Add to Cart')]")
    public WebElement buttonAddToCart;

    @FindBy(xpath = "//button[contains(text(), 'Cart') and not(contains(text(), 'Add to Cart'))]")
    public WebElement buttonOpenCartPage;

    @FindBy(xpath = "//button[contains(text(), 'Cart') and not(contains(text(), 'Add to Cart'))]//child::label")
    public WebElement labelCart;

    public WebElement titleProductDynamic(String productName) {
        return webDriver.findElement(By.xpath("//h2[contains(text(), '" + productName + "')]"));
    }

    public ProductDisplayObject(WebDriver webDriver) {
        super(webDriver);

        PageFactory.initElements(webDriver, this);
    }
}
