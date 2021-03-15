package com.project.money_care.model;

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

    private double amount;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;






}
