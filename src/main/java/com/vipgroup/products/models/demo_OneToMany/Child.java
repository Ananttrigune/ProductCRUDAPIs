package com.vipgroup.products.models.demo_OneToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Child {
    @Id
    int id;
    String name;
}
