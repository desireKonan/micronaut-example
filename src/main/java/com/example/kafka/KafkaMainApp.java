package com.example.kafka;

import io.micronaut.context.ApplicationContext;

public class KafkaMainApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.run();
        ProductClient client = applicationContext.getBean(ProductClient.class);
        client.sendProduct("Nike", "Blue trainers");
    }
}
