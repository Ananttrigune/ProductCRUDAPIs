package com.vipgroup.products.models.ManyToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Student {
    @Id
    int id;
    String name;
    @ManyToMany
    List<Teacher> t;
}