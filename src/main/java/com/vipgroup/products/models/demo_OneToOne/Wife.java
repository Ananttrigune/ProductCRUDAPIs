package com.vipgroup.products.models.demo_OneToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Wife {
    @Id
    int id;
    String name;
}
