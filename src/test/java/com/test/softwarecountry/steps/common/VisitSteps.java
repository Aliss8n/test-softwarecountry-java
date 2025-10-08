package com.test.softwarecountry.steps.common;

import com.test.softwarecountry.factory.utils.DriverManager;
import com.test.softwarecountry.factory.utils.Paths;

import io.cucumber.java.en.Given;

public class VisitSteps {

    private final Paths paths = new Paths();

    @Given("I visit {string}")
    public void iVisit(String page) {
        DriverManager.getDriver().get(paths.getWebUrl(page));
    }
}
