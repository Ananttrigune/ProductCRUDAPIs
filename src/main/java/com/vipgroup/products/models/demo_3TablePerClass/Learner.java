package com.vipgroup.products.models.demo_3TablePerClass;

import jakarta.persistence.Entity;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity(name="tpc_learners")
public class Learner extends User {
    private String college;
    private String company;
}