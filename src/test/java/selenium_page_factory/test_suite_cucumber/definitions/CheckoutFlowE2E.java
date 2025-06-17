package selenium_page_factory.test_suite_cucumber.definitions;

import com.demo.testng.program.selenium_page_factory.base.BaseTestSuite;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium_page_factory.pages.CartPage;
import selenium_page_factory.pages.CheckoutPage;
import selenium_page_factory.pages.DashboardPage;
import selenium_page_factory.pages.LoginPage;
import selenium_page_factory.pages.OrderPage;
import selenium_page_factory.pages.ProductDisplayPage;

public class CheckoutFlowE2E extends BaseTestSuite {
    public String url;
    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public ProductDisplayPage productDisplayPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public OrderPage orderPage;

    @Given("This website {string} for checkout flow")
    public void setUrl(String url) {
        this.url = url;
    }

    @Then("Open that website for checkout flow")
    public void openUrl() {
        super.setup();
        this.openUrl(this.url);
    }

    @And("Init all pages for run automation for checkout flow")
    public void initAllPages() {
        this.loginPage = new LoginPage(webDriver, wait);
        this.dashboardPage = new DashboardPage(webDriver, wait);
        this.productDisplayPage = new ProductDisplayPage(webDriver, wait);
        this.cartPage = new CartPage(webDriver, wait);
        this.checkoutPage = new CheckoutPage(webDriver, wait);
        this.orderPage = new OrderPage(webDriver, wait);
    }

    @When("Input email {string} and password {string} in login page for checkout flow")
    public void inputEmailAndPassword(String email, String password) {
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
    }

    @Then("Click login button for checkout flow")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("Search product with keyword {string} for checkout flow")
    public void searchProduct(String name) {
        dashboardPage.doSearch(name);
    }

    @Then("Click view product for checkout flow")
    public void viewProduct() {
        dashboardPage.clickButtonViewProduct();
    }

    @And("Verify product name, to make sure the product {string} is shown for checkout flow")
    public void verifyProductName(String name) {
        productDisplayPage.verifyDataProduct(name);
    }

    @Then("Click ATC button for checkout flow")
    public void clickATCProduct() throws InterruptedException {
        productDisplayPage.clickATCButton();
    }

    @Then("Click checkout button for checkout flow")
    public void clickCheckoutButton() {
        cartPage.clickButtonCheckout();
    }

    @Then("Enter the details payment for checkout flow")
    public void enterPaymentDetail() {
        checkoutPage.enterCVV();
        checkoutPage.enterNameCard();
        checkoutPage.enterCountry();
        checkoutPage.clickRecomendationCountry();
    }

    @Then("Click place order button for checkout flow")
    public void clickPlaceOrder() {
        checkoutPage.clickPlaceOrder();
    }

    @And("Verify order, to make sure {string} successfully ordered")
    public void verifyOrder(String productName) throws InterruptedException {
        orderPage.verifyOrderCreated(productName);
    }

    @Then("Teardown the test for checkout flow")
    public void teardown() {
        super.teardown();
    }
}
