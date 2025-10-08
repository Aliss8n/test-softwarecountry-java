package com.test.softwarecountry.factory.services;

import com.test.softwarecountry.factory.utils.Paths;
import com.test.softwarecountry.model.request.BreedsHeader;
import com.test.softwarecountry.model.response.breeds.BreedsResponse;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BreedsService {

    private final Paths paths = new Paths();

    public BreedsResponse requestGetBreeds(BreedsHeader breedsHeader) {
        try {
            log.info("Getting breeds information");

            Response response =
                    RestAssured
                            .given()
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON)
                            .pathParam("limit", breedsHeader.getLimit())
                            .when()
                            .get(paths.getApiUrl("BREEDS_API"))
                            .then()
                            .log().ifError()
                            .extract().response();

            BreedsResponse breedsResponse = response.as(BreedsResponse.class);
            breedsResponse.setStatusCode(response.getStatusCode());

            return breedsResponse;
        } catch (Exception e) {
            String errorMessage = "Unexpected error getting breeds information: " + e.getMessage();
            log.error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }
}
