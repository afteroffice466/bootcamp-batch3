package com.demo.testng.program.selenium_page_factory.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.testng.program.selenium_page_factory.constant.Env;

public abstract class BaseTestSuite {
    public WebDriver webDriver;
    public Wait<WebDriver> wait;

    public void setup() {
        System.out.println("Init base page...");
        System.setProperty("webdriver.chrome.driver", Env.DRIVER_PATH);

        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    public void openUrl(String url) {
        webDriver.get(url);
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void teardown() {
        webDriver.close();
    }
}
