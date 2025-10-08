package com.test.softwarecountry.factory.services;

import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.test.softwarecountry.factory.utils.DriverManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowserService {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");
        boolean isHeadless = Boolean.parseBoolean(
                System.getProperty("HEADLESS", System.getenv().getOrDefault("HEADLESS", "true"))
                                                 );

        log.info("Creating a {} browser driver (headless: {})", browser, isHeadless);

        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                if (isHeadless) {
                    options.addArguments("--headless=new");
                }
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--user-data-dir=/tmp/profile-" + UUID.randomUUID());
                driver = new ChromeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (isHeadless) {
                    options.addArguments("-headless");
                }
                driver = new FirefoxDriver(options);
            }
            default -> throw new RuntimeException("Browser is not supported: " + browser);
        }

        DriverManager.setDriver(driver);
        log.info("Browser driver for {} created successfully", browser);
        return driver;
    }
}
