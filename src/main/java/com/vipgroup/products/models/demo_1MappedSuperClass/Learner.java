package com.vipgroup.products.models.demo_1MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity(name = "msc_learners")
public class Learner extends User {
    private String college;
    private String company;
}