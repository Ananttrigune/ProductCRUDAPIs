package com.vipgroup.products.models.demo_OneToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Mother {
    @Id
    int id;
    String name;
    @OneToMany
    List<Child> c;
}
