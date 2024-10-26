package com.vipgroup.products.models.demo_OneToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Husband {
    @Id
    int id;
    String name;
    @OneToOne
    Wife w;
}