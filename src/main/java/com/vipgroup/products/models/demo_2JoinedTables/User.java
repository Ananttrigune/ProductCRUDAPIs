package com.vipgroup.products.models.demo_2JoinedTables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name="jt_users")
public class User {
    @Id
    private long id;
    private String name;
    private String email;
    private String password;
}
