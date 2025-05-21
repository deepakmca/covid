package com.deepak.covid.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CovidControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CovidController covidController;

    public CovidControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCovidSummary_Success() {
        // Arrange
        String apiUrl = "https://disease.sh/v3/covid-19/countries/india";
        Object mockResponse = new Object(); // Replace with actual response structure if needed
        when(restTemplate.getForEntity(apiUrl, Object.class))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        // Act
        ResponseEntity<Object> response = covidController.getCovidSummary();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(restTemplate, times(1)).getForEntity(apiUrl, Object.class);
    }

    @Test
    void testGetCovidSummary_Failure() {
        // Arrange
        String apiUrl = "https://disease.sh/v3/covid-19/countries/india";
        when(restTemplate.getForEntity(apiUrl, Object.class))
                .thenThrow(new RestClientException("API is unreachable"));

        // Act
        ResponseEntity<Object> response = covidController.getCovidSummary();

        // Assert
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("Unable to fetch data from the API: API is unreachable", response.getBody());
        verify(restTemplate, times(1)).getForEntity(apiUrl, Object.class);
    }
}