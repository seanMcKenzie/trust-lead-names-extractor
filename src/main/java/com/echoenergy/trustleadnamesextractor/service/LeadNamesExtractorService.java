package com.echoenergy.trustleadnamesextractor.service;


import com.echoenergy.trustleadnamesextractor.model.Entity;
import com.echoenergy.trustleadnamesextractor.model.NamedEntityRequest;
import com.echoenergy.trustleadnamesextractor.model.NamedEntityResponse;
import com.echoenergy.trustleadnamesextractor.util.NameUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LeadNamesExtractorService {

    public List<Entity> getLeadNames (NamedEntityRequest request){

        List<Entity> entities = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        request.setText(NameUtil.formatText(request.getText()));
        request.setModel("en");

        HttpEntity<NamedEntityRequest> requestEntity = new HttpEntity<>(request, requestHeaders);

        ResponseEntity<List<NamedEntityResponse>> responseEntity = restTemplate.exchange(
                "http://35.230.169.74:80/ent",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<NamedEntityResponse>>() {}
        );

        List<NamedEntityResponse> namedEntityResponses = responseEntity.getBody();

        for(NamedEntityResponse namedEntityResponse : namedEntityResponses){
            Entity entity = new Entity();
            if(namedEntityResponse.getType().equalsIgnoreCase("PERSON") &&
                    !namedEntityResponse.getText().trim().toLowerCase().contains("successor")){
                if(namedEntityResponse.getText().toLowerCase().endsWith("trust")){
                    String fullName = NameUtil.filter(namedEntityResponse.getText());
                    entity.setFirstName(NameUtil.extractFirstName(fullName));
                    entity.setMiddleName(NameUtil.extractMiddleName(fullName));
                    entity.setLastName(NameUtil.extractLastName(fullName));
                }else{
                    entity.setFirstName(NameUtil.extractFirstName(namedEntityResponse.getText()));
                    entity.setMiddleName(NameUtil.extractMiddleName(namedEntityResponse.getText()));
                    entity.setLastName(NameUtil.extractLastName(namedEntityResponse.getText()));
                }
                entities.add(entity);
            }
            if(namedEntityResponse.getType().equalsIgnoreCase("ORG") &&
                    !namedEntityResponse.getText().trim().toLowerCase().contains("successor")){
                if(namedEntityResponse.getText().toLowerCase().endsWith("trust")){
                    String orgName = NameUtil.filter(namedEntityResponse.getText());
                    entity.setOrgName(orgName);
                }else{
                    entity.setOrgName(namedEntityResponse.getText());
                }
                entities.add(entity);
            }
        }

        return entities;
    }

}
