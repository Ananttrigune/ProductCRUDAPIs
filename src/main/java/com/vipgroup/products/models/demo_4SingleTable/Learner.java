package com.vipgroup.products.models.demo_4SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity
@DiscriminatorValue("LEARNER")
public class Learner extends User {
    private String college;
    private String company;
}