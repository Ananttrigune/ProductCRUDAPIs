package com.vipgroup.products.models.demo_3TablePerClass;

import jakarta.persistence.Entity;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity(name="tpc_tas")
public class TA extends User {
    private int noOfQuestions;
    private String college;
}