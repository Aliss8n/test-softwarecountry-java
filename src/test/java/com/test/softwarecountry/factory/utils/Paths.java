package com.test.softwarecountry.factory.utils;

import java.lang.reflect.Field;

public class Paths {

    public static final String CONTACT_PAGE = "/qa-test-contact";
    public static final String BREEDS_API = "/breeds?limit={limit}";

    private final PropertiesProvider propertiesProvider = new PropertiesProvider();

    public String getWebUrl(String urlPath) {
        try {
            Field field = Paths.class.getField(urlPath);
            String endpoint = (String) field.get(null);
            return propertiesProvider.getWebyUrl() + endpoint;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid url path name: " + urlPath, e);
        }
    }

    public String getApiUrl(String endpointName) {
        try {
            Field field = Paths.class.getField(endpointName);
            String endpoint = (String) field.get(null);
            return propertiesProvider.getApiUrl() + endpoint;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid endpoint name: " + endpointName, e);
        }
    }
}
