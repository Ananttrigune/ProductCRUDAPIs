package com.vipgroup.products.models.demo_3TablePerClass;

import jakarta.persistence.Entity;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity(name="tpc_mentors")
public class Mentor extends User {
    private double avg_rating;
    private String currentCompany;
}