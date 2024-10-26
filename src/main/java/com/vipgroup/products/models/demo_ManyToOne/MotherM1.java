package com.vipgroup.products.models.demo_ManyToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MotherM1 {
    @Id
    int id;
    String name;
}
