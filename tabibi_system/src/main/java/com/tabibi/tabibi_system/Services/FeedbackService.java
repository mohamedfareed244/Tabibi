package com.tabibi.tabibi_system.Services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tabibi.tabibi_system.Models.Feedback;

@Service
public class FeedbackService {
    private RestTemplate restTemplate;
    private String baseUrl = "http://localhost:8083";
    
    FeedbackService()
    {
        this.restTemplate = new RestTemplate(); 

    }
     public List<Feedback> findAll() {
        String url = baseUrl + "/feedback";
        return this.restTemplate.exchange(
                url, // Endpoint URL
                HttpMethod.GET, // HTTP request method
                null, // Request body
                new ParameterizedTypeReference<List<Feedback>>() {
                }).getBody(); // Response body converted to List<Post>
    }

}
