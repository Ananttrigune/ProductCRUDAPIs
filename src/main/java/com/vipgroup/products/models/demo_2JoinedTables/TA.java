package com.vipgroup.products.models.demo_2JoinedTables;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity(name="jt_tas")
@PrimaryKeyJoinColumn(name="user3_id")
public class TA extends User {
    private int noOfQuestions;
    private String college;
}