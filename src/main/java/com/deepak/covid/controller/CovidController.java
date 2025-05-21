package com.deepak.covid.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class CovidController {

    private RestTemplate restTemplate;

    public CovidController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/summary")
    public ResponseEntity<Object> getCovidSummary() {
        try {
            String apiUrl = "https://disease.sh/v3/covid-19/countries/india";
            ResponseEntity<Object> response = restTemplate.getForEntity(apiUrl, Object.class);
            return ResponseEntity.ok(response.getBody());
        } catch (RestClientException e) {
            return ResponseEntity.status(503).body("Unable to fetch data from the API: " + e.getMessage());
        }
    }
}
