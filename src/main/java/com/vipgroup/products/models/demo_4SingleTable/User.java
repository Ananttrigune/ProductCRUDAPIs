package com.vipgroup.products.models.demo_4SingleTable;

import jakarta.persistence.*;
import lombok.Data;

/*@Getter
@Setter*/
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType", discriminatorType = DiscriminatorType.STRING)
@Entity
public class User {
    @Id
    private long id;
    private String name;
    private String email;
    private String password;
}
