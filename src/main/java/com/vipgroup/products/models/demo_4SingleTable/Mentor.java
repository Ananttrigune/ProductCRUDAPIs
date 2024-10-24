package com.vipgroup.products.models.demo_4SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity
@DiscriminatorValue("MENTOR")
public class Mentor extends User {
    private double avg_rating;
    private String currentCompany;
}