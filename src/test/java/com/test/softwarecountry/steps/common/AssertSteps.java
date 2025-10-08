package com.test.softwarecountry.steps.common;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.test.softwarecountry.factory.utils.ComponentMapper;
import com.test.softwarecountry.factory.utils.DriverManager;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class AssertSteps {

    @Then("I receive the invalid message {string} in the {string}")
    public void iReceiveTheInvalidMessageInThe(String message, String element) {
        String selector = ComponentMapper.getSelectorByFieldName(element);
        WebElement input = DriverManager.getDriver().findElement(By.cssSelector(selector));

        WebElement errorMessageElement = (WebElement) ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].closest('.contact-form-item').querySelector('.contact-form-error');", input);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(errorMessageElement));
        String actualMessage = errorMessageElement.getText().trim();

        Assertions.assertEquals(message, actualMessage, "Invalid validation message for input '" + element + "'");
    }

    @And("I have the {string} button {string}")
    public void iHaveTheButton(String element, String state) {
        String selector = ComponentMapper.getSelectorByFieldName(element);
        WebElement button = DriverManager.getDriver().findElement(By.cssSelector(selector));
        boolean isEnabled = button.isEnabled();

        switch (state) {
            case "enabled":
                if (!isEnabled)
                    throw new AssertionError("Expected the button '" + element + "' to be enabled, but it was disabled.");
                break;
            case "disabled":
                if (isEnabled)
                    throw new AssertionError("Expected the button '" + element + "' to be disabled, but it was enabled.");
                break;
            default:
                throw new IllegalArgumentException("Invalid state '" + state + "'. Expected 'enabled' or 'disabled'.");
        }
    }

    @Then("I receive the required message in the {string}")
    public void iReceiveTheRequiredMessageInThe(String element) {
        String selector = ComponentMapper.getSelectorByFieldName(element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));

        Boolean isValid = (Boolean) ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].validity.valid;", input);

        if (Boolean.TRUE.equals(isValid)) {
            throw new AssertionError("Expected input '" + element + "' to be invalid, but it is valid.");
        }
    }

    @And("I receive the validation message {string} in the {string}")
    public void iReceiveTheValidationMessageInThe(String message, String element) {
        String selector = ComponentMapper.getSelectorByFieldName(element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));

        String errorSelector = selector + "Error";
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(errorSelector)));

        String actualText = errorElement.getText().trim();
        Assertions.assertTrue(errorElement.isDisplayed(), "Expected validation message to be visible for element '" + element + "'");
        Assertions.assertEquals(message, actualText, "Invalid validation message for input '" + element + "'");
    }

    @Then("I receive the invalid message in the {string}")
    public void iReceiveTheInvalidMessageInThe(String element) {
        String selector = ComponentMapper.getSelectorByFieldName(element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));

        Boolean isValid = (Boolean) ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].validity.valid", input);

        if (Boolean.TRUE.equals(isValid)) {
            throw new AssertionError("Expected input '" + element + "' to be invalid, but it is valid.");
        }
    }
}
