package com.vipgroup.products.models.demo_3TablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;


/*@Getter
@Setter*/
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name="tpc_users")
public class User {
    @Id
    private long id;
    private String name;
    private String email;
    private String password;

    public static void main(String[] args) {
        User user = new User();
    }
}
