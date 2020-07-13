package com.asa.api.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "CARS")
public class Car {
    @Id
    @Column(name = "CAR_ID", length = 36, nullable = false, insertable = false, updatable = false)
    private String id;
    @Column(name = "CAR_CATEGORY", length = 25, nullable = false)
    @NotEmpty
    private String category;
    @Column(name = "CAR_MARK", length = 25, nullable = false)
    @NotEmpty
    private String mark;
    @Column(name = "CAR_MODEL", length = 25, nullable = false)
    @NotEmpty
    private String model;
    @Column(name = "CAR_ENERGY", length = 2, nullable = false)
    @NotEmpty
    private String energy;
    @Column(name = "CAR_YEAR", length = 4, nullable = false)
    @NotNull
    private Integer year;
    @Column(name = "CAR_POWER", length = 4, nullable = false)
    @NotNull
    private Integer power;
}
