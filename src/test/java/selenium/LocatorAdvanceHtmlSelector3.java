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

public class LocatorAdvanceHtmlSelector3 {
        public WebDriver webDriver;

        @BeforeSuite
        public void startBrowser() {
                System.out.println("Browser Start...");
                System.setProperty("webdriver.chrome.driver",
                                "/Users/filla/bootcamp-batch3/chromedriver-mac-arm64/chromedriver");

                webDriver = new ChromeDriver();

                webDriver.get("https://rahulshettyacademy.com/AutomationPractice");
                webDriver.manage().window().fullscreen();
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        @Test
        public void iFrameAndAlertTest() throws Exception {
                // Case handle pop up
                // Thread.sleep(Duration.ofSeconds(5));

                // Set<String> windows = webDriver.getWindowHandles();

                // Iterator<String> inIterator = windows.iterator();
                // String mainWindow = "";
                // while (inIterator.hasNext()) {
                // mainWindow = inIterator.next();
                // System.out.println("[DEBUG] => " + inIterator.next());
                // }

                Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

                WebElement iFrame = webDriver.findElement(By.xpath("//iframe[@id='courses-iframe']"));
                // Actions actions = new Actions(webDriver);
                // actions.scrollToElement(iFrame).perform();
                wait.until(d -> iFrame.isDisplayed());
                webDriver.switchTo().frame(iFrame);
                // webDriver.switchTo().frame("courses-iframe");

                WebElement mainMenuInIframe = webDriver
                                .findElement(By.xpath("//nav[@class='main-menu']//descendant::a[text()='Courses']"));
                wait.until(d -> mainMenuInIframe.isDisplayed());
                mainMenuInIframe.click();

                Thread.sleep(Duration.ofSeconds(5));

                webDriver.switchTo().defaultContent();

                WebElement inputAlert = webDriver.findElement(By.xpath("//input[@id='name']"));
                // actions.scrollToElement(inputAlert).perform();
                wait.until(d -> inputAlert.isDisplayed());
                inputAlert.sendKeys("filla");
                WebElement buttonAlert = webDriver.findElement(By.xpath("//input[@id='alertbtn']"));
                wait.until(d -> buttonAlert.isDisplayed());
                buttonAlert.click();

                System.out.println(webDriver.switchTo().alert().getText());

                Thread.sleep(Duration.ofSeconds(5));

                webDriver.switchTo().alert().accept();
        }

        @AfterSuite
        public void closeBrowser() throws Exception {
                Thread.sleep(Duration.ofSeconds(2));
                webDriver.close();
        }
}
