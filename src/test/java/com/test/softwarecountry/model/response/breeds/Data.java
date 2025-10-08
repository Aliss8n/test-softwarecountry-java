package com.test.softwarecountry.model.response.breeds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    private String breed;
    private String country;
    private String origin;
    private String coat;
    private String pattern;
}
