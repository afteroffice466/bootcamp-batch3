package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class DashboardObject extends BaseObject {
    @FindBy(xpath = "//section[@id='sidebar']//descendant::input[@name='search']")
    public WebElement inputSearch;

    @FindBy(xpath = "//section[@id='products']//descendant::button[contains(text(), 'View')]")
    public WebElement buttonViewProduct;

    public DashboardObject(WebDriver webDriver) {
        super(webDriver);

        PageFactory.initElements(webDriver, this);
    }
}
