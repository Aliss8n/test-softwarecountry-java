package com.test.softwarecountry.steps.common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.test.softwarecountry.factory.utils.ComponentMapper;
import com.test.softwarecountry.factory.utils.DriverManager;

import io.cucumber.java.en.And;

public class SelectSteps {

    @And("I select {string} in the {string}")
    public void iSelectInThe(String optionText, String element) {
        String selector = ComponentMapper.getSelectorByFieldName(element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));

        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));

        Select select = new Select(dropdown);
        select.selectByVisibleText(optionText);
    }
}
