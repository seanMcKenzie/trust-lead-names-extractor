package com.echoenergy.trustleadnamesextractor.model;

import lombok.Data;

@Data
public class NamedEntityResponse {

    private String type;
    private String text;
    private Integer start;
    private Integer end;
}
