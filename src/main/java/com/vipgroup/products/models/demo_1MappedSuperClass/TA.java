package com.vipgroup.products.models.demo_1MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity(name="msc_TAs")
public class TA extends User {
    private int noOfQuestions;
    private String college;
}