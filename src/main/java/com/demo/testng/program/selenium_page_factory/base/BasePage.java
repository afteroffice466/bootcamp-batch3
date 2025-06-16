package com.demo.testng.program.selenium_page_factory.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public abstract class BasePage {
    public WebDriver webDriver;
    public Wait<WebDriver> wait;

    public BasePage(WebDriver webDriver, Wait<WebDriver> wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }
}
