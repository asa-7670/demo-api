package com.asa.api.demo.model;

import com.asa.api.demo.constant.car.Category;
import com.asa.api.demo.constant.car.Energy;
import com.asa.api.demo.constant.car.Mark;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
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
    @Column(name = "CAR_CATEGORY", length = 2, nullable = false)
    @NotEmpty
    private String category;
    @Column(name = "CAR_MARK", length = 3, nullable = false)
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

    @Transient
    private String categoryLabel;
    public String getCategoryLabel(){
        return StringUtils.isEmpty(this.category) ? null : Category.of(this.category).name();
    }
    @Transient
    private String markLabel;
    public String getMarkLabel() {
        return StringUtils.isEmpty(this.mark) ? null : Mark.of(this.mark).name();
    }
    @Transient
    private String EnergyLabel;
    public String getEnergyLabel(){
        return StringUtils.isEmpty(this.energy) ? null : Energy.of(this.energy).name();
    }

}
