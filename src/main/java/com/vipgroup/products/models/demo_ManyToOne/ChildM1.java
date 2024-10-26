package com.vipgroup.products.models.demo_ManyToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ChildM1 {
    @Id
    int id;
    String name;
    @ManyToOne
    MotherM1 mother;
}
