package com.vipgroup.products.models.demo_1MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity(name="msc_mentors")
public class Mentor extends User {
    private double avg_rating;
    private String currentCompany;
}