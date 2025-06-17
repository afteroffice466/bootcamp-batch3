package selenium_scenario;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CheckoutFlowE2E {
    public WebDriver webDriver;
    public Wait<WebDriver> wait;

    public String productName;
    public String cvvCode;
    public String nameCard;
    public String country;

    @BeforeSuite
    public void startBrowser() {
        System.out.println("Browser Start...");
        System.setProperty("webdriver.chrome.driver",
                "/Users/filla/bootcamp-batch3/chromedriver-mac-arm64/chromedriver");

        webDriver = new ChromeDriver();

        webDriver.get("https://rahulshettyacademy.com/client");
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        productName = "ZARA COAT 3";
    }

    @Test
    public void doLogin() {
        WebElement inputEmail = webDriver.findElement(By.xpath("//input[@id='userEmail']"));
        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@id='userPassword']"));
        WebElement buttonLogin = webDriver.findElement(By.xpath("//input[@id='login']"));

        wait.until(d -> inputEmail.isDisplayed());
        inputEmail.sendKeys("simanjuntakalbert57@gmail.com");

        wait.until(d -> inputPassword.isDisplayed());
        inputPassword.sendKeys("XBf@rWNvByn!#K8");

        wait.until(d -> buttonLogin.isDisplayed());
        buttonLogin.click();
    }

    @Test(dependsOnMethods = "doLogin")
    public void searchAndViewProduct() {
        WebElement inputSearch = webDriver
                .findElement(By.xpath("//section[@id='sidebar']//descendant::input[@name='search']"));
        WebElement buttonViewProduct = webDriver
                .findElement(By.xpath("//section[@id='products']//descendant::button[contains(text(), 'View')]"));

        wait.until(d -> inputSearch.isDisplayed());
        inputSearch.sendKeys(productName);
        inputSearch.sendKeys(Keys.ENTER);

        wait.until(d -> buttonViewProduct.isDisplayed());
        buttonViewProduct.click();
    }

    @Test(dependsOnMethods = "searchAndViewProduct")
    public void verifyDataProductInProductViewPage() {
        WebElement titleProduct = webDriver.findElement(By.xpath("//h2[contains(text(), '" + productName + "')]"));
        Boolean elementIsPresent = wait.until(d -> titleProduct.isDisplayed());
        Assert.assertTrue(elementIsPresent, "element title product not present");
    }

    @Test(dependsOnMethods = "verifyDataProductInProductViewPage")
    public void doATCAndOpenCartPage() throws Exception {
        WebElement buttonAddToCart = webDriver.findElement(By.xpath("//button[contains(text(), 'Add to Cart')]"));
        WebElement buttonOpenCartPage = webDriver
                .findElement(By.xpath("//button[contains(text(), 'Cart') and not(contains(text(), 'Add to Cart'))]"));
        WebElement labelCart = webDriver
                .findElement(By.xpath(
                        "//button[contains(text(), 'Cart') and not(contains(text(), 'Add to Cart'))]//child::label"));

        wait.until(d -> buttonAddToCart.isDisplayed());
        buttonAddToCart.click();

        Thread.sleep(Duration.ofSeconds(3));

        wait.until(d -> labelCart.isDisplayed());
        if (labelCart.getText().equals("1")) {
            wait.until(d -> buttonOpenCartPage.isDisplayed());
            buttonOpenCartPage.click();
        } else {
            System.out.println("labelCart => " + labelCart);
            Assert.assertTrue(false, "cart label not increment");
        }
    }

    @Test(dependsOnMethods = "doATCAndOpenCartPage")
    public void doCheckout() {
        WebElement buttonCheckout = webDriver.findElement(By.xpath("//button[contains(text(), 'Checkout')]"));

        wait.until(d -> buttonCheckout.isDisplayed());
        buttonCheckout.click();
    }

    @Test(dependsOnMethods = "doCheckout")
    public void doPlaceOrder() throws Exception {
        WebElement cvvInput = webDriver
                .findElement(By.xpath("//div[contains(text(), 'CVV Code')]//following-sibling::input"));
        WebElement nameOnCard = webDriver
                .findElement(By.xpath("//div[contains(text(), 'Name on Card')]//following-sibling::input"));
        WebElement selectCountry = webDriver.findElement(By.xpath("//input[contains(@placeholder, 'Select Country')]"));
        WebElement buttonPlaceOrder = webDriver.findElement(
                By.xpath(
                        "//a[contains(text(), 'Place Order')]"));

        wait.until(d -> cvvInput.isDisplayed());
        cvvInput.sendKeys("123");

        wait.until(d -> nameOnCard.isDisplayed());
        nameOnCard.sendKeys("andre");

        wait.until(d -> selectCountry.isDisplayed());
        selectCountry.sendKeys("indonesia");

        WebElement sectionRecomendationCountry = webDriver
                .findElement(By.xpath(
                        "//input[contains(@placeholder, 'Select Country')]//following-sibling::section"));

        wait.until(d -> sectionRecomendationCountry.isDisplayed());
        sectionRecomendationCountry.click();

        wait.until(d -> buttonPlaceOrder.isDisplayed());
        buttonPlaceOrder.click();
    }

    @Test(dependsOnMethods = "doPlaceOrder")
    public void verifyOrderCreated() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));

        WebElement titleProduct = webDriver.findElement(By.xpath("//*[contains(text(), '" + productName + "')]"));
        WebElement titleThanks = webDriver.findElement(By.xpath("//*[contains(text(), 'Thankyou for the order')]"));

        Boolean elementIsPresentProductName = wait.until(d -> titleProduct.isDisplayed());
        Assert.assertTrue(elementIsPresentProductName, "element title product not present");

        Boolean elementIsPresentThanks = wait.until(d -> titleThanks.isDisplayed());
        Assert.assertTrue(elementIsPresentThanks, "element thanks not present");
    }

    @AfterSuite
    public void closeBrowser() throws Exception {
        Thread.sleep(Duration.ofSeconds(2));
        webDriver.close();
    }
}
