package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.CartObject;

public class CartPage extends BasePage {
    public CartObject cartObject;

    public CartPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.cartObject = new CartObject(webDriver);
    }

    public void clickButtonCheckout() {
        wait.until(d -> cartObject.buttonCheckout.isDisplayed());
        cartObject.buttonCheckout.click();
    }
    
}
