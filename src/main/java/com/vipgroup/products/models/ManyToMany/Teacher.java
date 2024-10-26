package com.vipgroup.products.models.ManyToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Teacher {
    @Id
    int id;
    String name;
}