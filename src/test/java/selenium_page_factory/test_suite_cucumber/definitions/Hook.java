package selenium_page_factory.test_suite_cucumber.definitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.testng.program.selenium_page_factory.constant.Env;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {
    public static WebDriver webDriver;
    public static Wait<WebDriver> wait;

    @Before
    public void beforeScenario() throws IOException {
        System.out.println("beforeScenario");

        String env = System.getProperty("env") == null ? "staging" : System.getProperty("env");
        System.out.println("env: " + env);
        if (!env.equals("staging") && !env.equals("production")) {
            env = "staging";
        }

        String currentWorkingDirectory = System.getProperty("user.dir");
        System.out.println("Current Directory = " + currentWorkingDirectory);
        FileInputStream fileInputStream = new FileInputStream(
                currentWorkingDirectory + "/src/test/resources/selenium_page_factory/" + env + ".properties");
        System.getProperties().load(fileInputStream);
        System.out.println(System.getProperty("browser"));
        System.out.println(System.getProperty("env"));
        System.out.println(System.getProperty("suiteXml"));

        if (System.getProperty("browser").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + Env.DRIVER_PATH);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            Hook.webDriver = new ChromeDriver(chromeOptions);
        } else {
            //
        }

        Hook.wait = new WebDriverWait(Hook.webDriver, Duration.ofSeconds(5));

        // throw new Error("this sample of error");
    }

    @After
    public void afterScenario() {
        System.out.println("afterScenario");
        if (Hook.webDriver != null) {
            Hook.webDriver.close();
        }
    }
}
