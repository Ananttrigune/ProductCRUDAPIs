package com.vipgroup.products.models.demo_2JoinedTables;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity(name="jt_learners")
@PrimaryKeyJoinColumn(name="user1_id")
public class Learner extends User {
    private String college;
    private String company;
}