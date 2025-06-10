package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LocatorAdvanceHtmlSelector2 {
    public WebDriver webDriver;

    @BeforeSuite
    public void startBrowser() {
        System.out.println("Browser Start...");
        System.setProperty("webdriver.chrome.driver",
                "/Users/filla/bootcamp-batch3/chromedriver-mac-arm64/chromedriver");

        webDriver = new ChromeDriver();

        webDriver.get("https://rahulshettyacademy.com/dropdownsPractise");
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void searchFlights() throws Exception {
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        WebElement departureCityDropdown = webDriver
                .findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));
        wait.until(d -> departureCityDropdown.isDisplayed());
        departureCityDropdown.click();

        WebElement departureCityDropdownValue = webDriver.findElement(
                By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']//descendant::a[@value='AIP']"));
        wait.until(d -> departureCityDropdownValue.isDisplayed());
        departureCityDropdownValue.click();

        // Auto showing dropdown, no need to click
        // WebElement destinationCityDropdown = webDriver
        // .findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_destinationStation1_CTXT']"));
        // wait.until(d -> destinationCityDropdown.isDisplayed());
        // destinationCityDropdown.click();

        WebElement destinationCityDropdownValue = webDriver.findElement(By
                .xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//descendant::a[@value='DEL']"));
        wait.until(d -> destinationCityDropdownValue.isDisplayed());
        destinationCityDropdownValue.click();

        WebElement dateOnboardCalendar = webDriver.findElement(
                By.xpath("//div[@id='ui-datepicker-div']//descendant::td[@data-month='4']//child::a[text()='8']"));
        wait.until(d -> dateOnboardCalendar.isDisplayed());
        dateOnboardCalendar.click();

        WebElement buttonSearch = webDriver.findElement(By.xpath("//input[@id='ctl00_mainContent_btn_FindFlights']"));
        wait.until(d -> buttonSearch.isDisplayed());
        buttonSearch.click();
        Thread.sleep(Duration.ofSeconds(5));
    }

    @AfterSuite
    public void closeBrowser() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        webDriver.close();
    }
}
