package com.test.softwarecountry.factory.utils;

import java.lang.reflect.Field;
import java.util.Map;

public class ComponentMapper {

    private static final Map<String, Class<?>> COMPONENTS = Map.of(
            "contact.input", com.test.softwarecountry.components.contact.Input.class,
            "contact.button", com.test.softwarecountry.components.contact.Button.class,
            "contact.select", com.test.softwarecountry.components.contact.Select.class,
            "contact.container", com.test.softwarecountry.components.contact.Container.class
                                                                  );

    public static String getSelectorByFieldName(String fieldName) {
        for (Class<?> clazz : COMPONENTS.values()) {
            try {
                Field field = clazz.getField(fieldName);
                return (String) field.get(null);
            } catch (NoSuchFieldException ignored) {
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot access field: " + fieldName, e);
            }
        }
        throw new IllegalArgumentException("Selector not found for: " + fieldName);
    }
}
