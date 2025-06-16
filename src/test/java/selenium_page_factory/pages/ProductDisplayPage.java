package selenium_page_factory.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.ProductDisplayObject;

public class ProductDisplayPage extends BasePage {
    public ProductDisplayObject productDisplayObject;

    public ProductDisplayPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.productDisplayObject = new ProductDisplayObject(webDriver);
    }

    public void verifyDataProduct(String productName) {
        Boolean elementIsPresent = wait.until(d -> productDisplayObject.titleProductDynamic(productName).isDisplayed());
        Assert.assertTrue(elementIsPresent, "element title product not present");
    }

    public void clickATCButton() throws InterruptedException {
        wait.until(d -> productDisplayObject.buttonAddToCart.isDisplayed());
        productDisplayObject.buttonAddToCart.click();

        Thread.sleep(Duration.ofSeconds(3));

        wait.until(d -> productDisplayObject.labelCart.isDisplayed());
        if (productDisplayObject.labelCart.getText().equals("1")) {
            wait.until(d -> productDisplayObject.buttonOpenCartPage.isDisplayed());
            productDisplayObject.buttonOpenCartPage.click();
        } else {
            System.out.println("labelCart => " + productDisplayObject.labelCart);
            Assert.assertTrue(false, "cart label not increment");
        }
    }

}
