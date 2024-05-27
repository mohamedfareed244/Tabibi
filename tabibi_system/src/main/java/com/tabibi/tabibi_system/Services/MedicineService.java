package com.tabibi.tabibi_system.Services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tabibi.tabibi_system.Models.Medicine;


@Service
public class MedicineService {
    private RestTemplate restTemplate;
    private String baseUrl = "http://localhost:8084";

    MedicineService()
    {
        this.restTemplate = new RestTemplate(); 

    }

       public List<Medicine> findAll() {
        String url = baseUrl + "/medicine";
        return this.restTemplate.exchange(
                url, // Endpoint URL
                HttpMethod.GET, // HTTP request method
                null, // Request body
                new ParameterizedTypeReference<List<Medicine>>() {
                }).getBody(); // Response body converted to List<Post>
    }

    public void save(Medicine medicine){
        String url = baseUrl + "/medicine/add";
        this.restTemplate.postForObject(url, medicine, Medicine.class);
    } 

}
