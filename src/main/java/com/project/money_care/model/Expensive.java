package com.project.money_care.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "expensive")
@Data
public class Expensive implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String category;

    private int amount;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    private Users user;






}
