package com.demo.testng.program.selenium_page_factory.base;

import org.openqa.selenium.WebDriver;

public abstract class BaseObject {
    public WebDriver webDriver;

    public BaseObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
