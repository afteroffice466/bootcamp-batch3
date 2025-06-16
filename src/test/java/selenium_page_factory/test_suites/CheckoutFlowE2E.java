package selenium_page_factory.test_suites;

import java.time.Duration;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.demo.testng.program.selenium_page_factory.base.BaseTestSuite;

import selenium_page_factory.pages.CartPage;
import selenium_page_factory.pages.CheckoutPage;
import selenium_page_factory.pages.DashboardPage;
import selenium_page_factory.pages.LoginPage;
import selenium_page_factory.pages.ProductDisplayPage;

public class CheckoutFlowE2E extends BaseTestSuite {
    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public ProductDisplayPage productDisplayPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    // init order page

    @BeforeSuite
    public void setup() {
        super.setup();
        this.openUrl("https://rahulshettyacademy.com/client");

        this.loginPage = new LoginPage(webDriver, wait);
        this.dashboardPage = new DashboardPage(webDriver, wait);
        this.productDisplayPage = new ProductDisplayPage(webDriver, wait);
        this.cartPage = new CartPage(webDriver, wait);
        this.checkoutPage = new CheckoutPage(webDriver, wait);
        // init order page
    }

    @Test
    public void doCheckoutFlow() throws InterruptedException {
        loginPage.fillEmail("simanjuntakalbert57@gmail.com");
        loginPage.fillPassword("XBf@rWNvByn!#K8");
        loginPage.clickLoginButton();

        dashboardPage.doSearch("ZARA COAT 3");
        dashboardPage.clickButtonViewProduct();

        productDisplayPage.verifyDataProduct("ZARA COAT 3");
        productDisplayPage.clickATCButton();

        cartPage.clickButtonCheckout();

        checkoutPage.enterCVV();
        checkoutPage.enterNameCard();
        checkoutPage.enterCountry();
        checkoutPage.clickRecomendationCountry();
        checkoutPage.clickPlaceOrder();

        // action order page

        Thread.sleep(Duration.ofSeconds(2));
    }

    @AfterSuite
    public void teardown() {
        super.teardown();
    }
}
