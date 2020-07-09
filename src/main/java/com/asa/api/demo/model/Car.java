package com.asa.api.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Car {
    private String id;
    private String type;
    private String name;
    private Integer power;
}
