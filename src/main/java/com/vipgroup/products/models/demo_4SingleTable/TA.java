package com.vipgroup.products.models.demo_4SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity
@DiscriminatorValue("TA")
public class TA extends User {
    private int noOfQuestions;
    private String collegePassOut;
}