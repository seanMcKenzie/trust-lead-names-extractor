package com.echoenergy.trustleadnamesextractor.service;


import com.echoenergy.trustleadnamesextractor.model.Entity;
import com.echoenergy.trustleadnamesextractor.model.NamedEntityRequest;
import com.echoenergy.trustleadnamesextractor.model.NamedEntityResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class LeadNamesExtractorService {

    public List<Entity> getLeadNames (NamedEntityRequest request){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<NamedEntityRequest> requestEntity = new HttpEntity<>(request, requestHeaders);

        ResponseEntity<List<NamedEntityResponse>> responseEntity = restTemplate.exchange(
                "http://35.230.169.74:80/ent",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<NamedEntityResponse>>() {}
        );


        return null;
    }

}
