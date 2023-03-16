package com.example.config;

import io.micronaut.context.annotation.Property;

@Property(name = "app.config")
public class AppConfig {

    public static String VERSION = "v1";

    public static String getVERSION() {
        return VERSION;
    }
}
