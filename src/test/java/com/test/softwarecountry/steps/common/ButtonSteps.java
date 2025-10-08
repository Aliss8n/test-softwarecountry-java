package com.test.softwarecountry.steps.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.test.softwarecountry.factory.utils.ComponentMapper;
import com.test.softwarecountry.factory.utils.DriverManager;

import io.cucumber.java.en.When;

public class ButtonSteps {

    @When("I click on the {string}")
    public void iClickOnThe(String element) {
        String selector = ComponentMapper.getSelectorByFieldName(element);
        WebElement checkbox = DriverManager.getDriver().findElement(By.cssSelector(selector));
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", checkbox);
    }
}
