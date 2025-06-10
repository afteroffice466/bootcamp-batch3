package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LocatorAdvanceHtmlSelector {
    public WebDriver webDriver;

    @BeforeSuite
    public void startBrowser() {
        System.out.println("Browser Start...");
        System.setProperty("webdriver.chrome.driver",
                "/Users/filla/bootcamp-batch3/chromedriver-mac-arm64/chromedriver");

        webDriver = new ChromeDriver();

        webDriver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void selectCurrency() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        WebElement webElement = webDriver
                .findElement(By.xpath("//select[@id='ctl00_mainContent_DropDownListCurrency']"));
        Select select = new Select(webElement);
        System.out.println("CURRENCY DROPDOWN SELECTED SIZE: " +
                select.getAllSelectedOptions().size());
        for (WebElement e : select.getAllSelectedOptions()) {
            System.out.println("CURRENCY DROPDOWN SELECTED VALUE: " + e.getText());
        }
        for (WebElement e : select.getOptions()) {
            System.out.println("CURRENCY DROPDOWN ALL VALUE: " + e.getText());
        }

        select.selectByVisibleText("AED");
        Assert.assertEquals("AED", select.getFirstSelectedOption().getText());
        select.selectByIndex(1);
    }

    // @Test(dependsOnMethods = "selectCurrency")
    @Test
    public void setPassengers() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        WebElement dropdownPassenger = webDriver.findElement(By.xpath("//div[@id='divpaxinfo']"));
        dropdownPassenger.click();
        Thread.sleep(Duration.ofSeconds(1));
        WebElement incrementAdult = webDriver.findElement(By.xpath("//span[@id='hrefIncAdt']"));
        incrementAdult.click();
        WebElement incrementChild = webDriver.findElement(By.xpath("//span[@id='hrefIncChd']"));
        incrementChild.click();
        WebElement incrementInfant = webDriver.findElement(By.xpath("//span[@id='hrefIncInf']"));
        incrementInfant.click();
        WebElement btn = webDriver.findElement(By.xpath("//input[@id='btnclosepaxoption']"));
        btn.click();

        // SAMPLE COMPLEX WAY
        // List<WebElement> optionPassengers = webDriver
        // .findElements(By.xpath("//div[@id='divpaxOptions']//child::div[contains(@class,
        // 'ad-row')]"));
        // for (WebElement optionPassenger : optionPassengers) {
        // String idOption = optionPassenger.getAttribute("id");
        // if (idOption != null && idOption != "") {
        // List<WebElement> optionDescendant = webDriver
        // .findElements(By.xpath("//div[@id='" + idOption +
        // "']//descendant::span[contains(@class, 'pax-add')]"));

        // // Debug =============
        // for (int i = 0; i < optionDescendant.size(); i++) {
        // System.out.println(idOption + " ==== " + i + " ---- " +
        // optionDescendant.get(i).getText());
        // }
        // // ================

        // Thread.sleep(Duration.ofSeconds(1));
        // optionDescendant.get(1).click();
        // } else {
        // WebElement btn =
        // webDriver.findElement(By.xpath("//input[@id='btnclosepaxoption']"));
        // btn.click();
        // }
        // }

        // WebElement fromInput =
        // webDriver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));
        // fromInput.click();
        // fromInput.sendKeys("am");
        // WebElement selectedValue =
        // webDriver.findElement(By.xpath("//div[contains(@class,
        // 'dropdownDiv')]//descendant::a[contains(text(), 'AIP')]"));

        // Thread.sleep(Duration.ofSeconds(2));

        // WebElement fromInputCalendar =
        // webDriver.findElement(By.xpath("//input[@id='ctl00_mainContent_view_date1']"));
        // fromInputCalendar.click();
        // List<WebElement> selectedValueCalendar =
        // webDriver.findElements(By.xpath("//table[contains(@class,
        // 'ui-datepicker-calendar')]//descendant::td[@data-month='4' and
        // @data-year='2019']//child::a[text()='5']"));
        // System.out.println(selectedValueCalendar.size());
    }

    @AfterSuite
    public void closeBrowser() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        webDriver.close();
    }
}
