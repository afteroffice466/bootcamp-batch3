package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.CheckoutObject;

public class CheckoutPage extends BasePage {
    public CheckoutObject checkoutObject;

    public CheckoutPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.checkoutObject = new CheckoutObject(webDriver);
    }

    public void enterCVV() {
        wait.until(d -> checkoutObject.cvvInput.isDisplayed());
        checkoutObject.cvvInput.sendKeys("123");
    }

    public void enterNameCard() {
        wait.until(d -> checkoutObject.nameOnCard.isDisplayed());
        checkoutObject.nameOnCard.sendKeys("andre");
    }

    public void enterCountry() {
        wait.until(d -> checkoutObject.selectCountry.isDisplayed());
        checkoutObject.selectCountry.sendKeys("indonesia");
    }

    public void clickRecomendationCountry() {
        wait.until(d -> checkoutObject.sectionRecomendationCountry.isDisplayed());
        checkoutObject.sectionRecomendationCountry.click();
    }

    public void clickPlaceOrder() {
        wait.until(d -> checkoutObject.buttonPlaceOrder.isDisplayed());
        checkoutObject.buttonPlaceOrder.click();
    }

}
