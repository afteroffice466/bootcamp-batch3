package selenium_page_factory.pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.DashboardObject;

public class DashboardPage extends BasePage {
    public DashboardObject dashboardObject;

    public DashboardPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.dashboardObject = new DashboardObject(webDriver);
    }

    public void doSearch(String value) {
        wait.until(d -> dashboardObject.inputSearch.isDisplayed());
        dashboardObject.inputSearch.sendKeys(value);
        dashboardObject.inputSearch.sendKeys(Keys.ENTER);
    }

    public void clickButtonViewProduct() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(1));
        wait.until(d -> dashboardObject.buttonViewProduct.isDisplayed());
        dashboardObject.buttonViewProduct.click();
    }

}
