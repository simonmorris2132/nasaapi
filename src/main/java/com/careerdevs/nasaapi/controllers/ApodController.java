package com.careerdevs.nasaapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.careerdevs.nasaapi.models.APODInfo;

@RestController
@RequestMapping("/api/apod")
public class ApodController {
    
    @Value("${NASA_API_KEY_PROP}")
    public String apiKey;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute() {
        return new ResponseEntity<>("Hey", HttpStatus.OK);
    }

    @GetMapping("/info")
    public APODInfo apodInfo(RestTemplate restTemplate) {
        
        String url = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey;

        APODInfo apod = restTemplate.getForObject(url, APODInfo.class);

        return apod;

    }

}
