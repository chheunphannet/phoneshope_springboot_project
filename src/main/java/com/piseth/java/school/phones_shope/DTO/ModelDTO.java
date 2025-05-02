package com.piseth.java.school.phones_shope.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) 
//when the field is null or not mapping it not appear
public class ModelDTO {
    private String name;
    private Integer brand_id;
    private String brandName;
    private Integer model_id;
}
