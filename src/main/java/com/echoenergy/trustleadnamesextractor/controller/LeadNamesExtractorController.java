package com.echoenergy.trustleadnamesextractor.controller;


import com.echoenergy.trustleadnamesextractor.model.Entity;
import com.echoenergy.trustleadnamesextractor.model.NamedEntityRequest;
import com.echoenergy.trustleadnamesextractor.service.LeadNamesExtractorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/v1/lead-names")
public class LeadNamesExtractorController {

    private final LeadNamesExtractorService leadNamesExtractorService;

    @PostMapping
    public ResponseEntity<List<Entity>> getLeadNames(@RequestBody NamedEntityRequest request) {
        return ResponseEntity.ok().body(leadNamesExtractorService.getLeadNames(request));
    }

}
