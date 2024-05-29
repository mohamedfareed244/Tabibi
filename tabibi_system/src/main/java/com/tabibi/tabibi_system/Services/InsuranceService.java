package com.tabibi.tabibi_system.Services;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import com.tabibi.tabibi_system.Models.Feedback;
import com.tabibi.tabibi_system.Models.Insurance;
import com.tabibi.tabibi_system.Models.Medicine;
@Service
public class InsuranceService {
   private RestTemplate restTemplate;
    private String baseUrl = "http://localhost:8085";
    InsuranceService()
    {
        this.restTemplate = new RestTemplate(); 

    }
    // public List<Insurance> findAll() {
    //     String url = baseUrl + "/insurance";
    //     return restTemplate.exchange(
    //             url,
    //             HttpMethod.GET,
    //             null,
    //             new ParameterizedTypeReference<List<Insurance>>() {}).getBody();
    // }
    public Insurance findAll() {
        String url = baseUrl + "/insurance";
        return this.restTemplate.exchange(
                url,
                HttpMethod.GET,
              null,
                new ParameterizedTypeReference<Insurance>() {}).getBody();
                
    }
     public Insurance findByemail(String email) {
    String url = baseUrl + "/insurance/" + email;

    
    return this.restTemplate.getForObject(url, Insurance.class);
    }

}
