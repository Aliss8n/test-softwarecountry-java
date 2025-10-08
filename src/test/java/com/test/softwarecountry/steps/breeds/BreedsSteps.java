package com.test.softwarecountry.steps.breeds;

import com.test.softwarecountry.factory.services.BreedsService;
import com.test.softwarecountry.model.request.BreedsHeader;
import com.test.softwarecountry.model.response.breeds.BreedsResponse;
import com.test.softwarecountry.validations.breeds.BreedsValidator;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class BreedsSteps {

    private static final BreedsValidator breedsValidator = new BreedsValidator();
    private final BreedsService breedsService = new BreedsService();
    private BreedsHeader breedsHeader;
    private BreedsResponse breedsResponse;

    @Given("I give a careers header with limit {int}")
    public void iGiveASCareersHeaderWithLimit(int limit) {
        breedsHeader = BreedsHeader.builder().limit(limit).build();
    }

    @When("I submit a request GET to Breeds endpoint")
    public void iSubmitARequestGETBreedsTo() {
        breedsResponse = breedsService.requestGetBreeds(breedsHeader);
    }

    @And("I successfully receive the breeds information")
    public void iSuccessfullyReceiveTheBreedsInformation() {
        breedsValidator.checkBreeds(breedsResponse, breedsHeader.getLimit());
    }
}
