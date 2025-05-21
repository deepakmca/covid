package com.deepak.covid.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RestTemplateConfigTest {

    @Test
    void testRestTemplateBeanCreation() {
        // Arrange
        ApplicationContext context = new AnnotationConfigApplicationContext(RestTemplateConfig.class);

        // Act
        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        // Assert
        assertNotNull(restTemplate, "RestTemplate bean should not be null");
    }
}