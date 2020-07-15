package com.project.money_care.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
//@Table(name = "users")
public class Users implements Serializable {

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9]+(\\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private boolean active;
   // @Enumerated(EnumType.STRING)
    private String role;

    public Users() {
        this.role = Roles.USER.name;
    }

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    Set<Incomes> incomesSet = new HashSet<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    Set<Expensive> expensiveSet = new HashSet<>();

    enum Roles {
    USER("USER"),
    MANAGER("MANAGER");

    private String name;

    Roles(String name) {
        this.name = name;
    }
}

}
