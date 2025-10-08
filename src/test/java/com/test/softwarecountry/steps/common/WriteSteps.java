package com.test.softwarecountry.steps.common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.test.softwarecountry.factory.utils.ComponentMapper;
import com.test.softwarecountry.factory.utils.DriverManager;

import io.cucumber.java.en.And;

public class WriteSteps {

    @And("I write {string} in the {string}")
    public void iWriteInThe(String text, String element) {
        String selector = ComponentMapper.getSelectorByFieldName(element);

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));

        input.clear();
        input.sendKeys(text);
    }
}
