package com.test.softwarecountry.factory.utils;

import org.openqa.selenium.WebDriver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThread.set(driver);
    }

    public static void quitDriver() {
        log.info("Closing browser driver");
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
        log.info("Browser driver closed successfully");
    }
}
