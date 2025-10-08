package com.test.softwarecountry.hooks.common;

import com.test.softwarecountry.factory.services.BrowserService;
import com.test.softwarecountry.factory.utils.DriverManager;
import com.test.softwarecountry.factory.utils.PropertiesProvider;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class BrowserHook {

    public static PropertiesProvider propertiesProvider = new PropertiesProvider();

    @BeforeAll
    public static void loadProperties() {
        propertiesProvider.loadProperties();
    }

    @Before("@frontend")
    public void openBrowser() {
        DriverManager.setDriver(BrowserService.createDriver());
        DriverManager.getDriver().manage().window().maximize();
    }

    @After("@frontend")
    public void closeBrowser() {
        DriverManager.quitDriver();
    }
}
