package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.OrderObject;

public class OrderPage extends BasePage {
    public OrderObject orderObject;

    public OrderPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.orderObject = new OrderObject(webDriver);
    }
}
