package com.asa.api.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    private String id;
    private String category;
    private String mark;
    private String model;
    private String energy;
    private Integer year;
    private Integer power;
}
