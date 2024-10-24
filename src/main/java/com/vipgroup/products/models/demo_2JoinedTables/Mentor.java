package com.vipgroup.products.models.demo_2JoinedTables;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Entity(name="jt_mentors")
@PrimaryKeyJoinColumn(name="user2_id")
public class Mentor extends User {
    private double avg_rating;
    private String currentCompany;
}