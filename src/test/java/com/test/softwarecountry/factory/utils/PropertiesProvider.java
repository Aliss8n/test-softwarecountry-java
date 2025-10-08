package com.test.softwarecountry.factory.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.http.client.utils.URIBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesProvider {

    public String protocol;
    public String baseUrl;
    public String apiUrl;

    public void loadProperties() {
        log.info("Loading properties file");
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String environment = System.getProperty("environment");
            protocol = prop.getProperty(environment + ".protocol");
            baseUrl = prop.getProperty(environment + ".baseUrl");
            apiUrl = prop.getProperty(environment + ".apiUrl");
            log.info("Properties file loaded successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getWebyUrl() {
        loadProperties();
        var uriBuilder = new URIBuilder();
        try {
            return uriBuilder
                    .setScheme(protocol)
                    .setHost(baseUrl)
                    .build().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String getApiUrl() {
        loadProperties();
        var uriBuilder = new URIBuilder();
        try {
            return uriBuilder
                    .setScheme(protocol)
                    .setHost(apiUrl)
                    .build().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
