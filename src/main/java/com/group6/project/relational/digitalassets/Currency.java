package com.group6.project.relational.digitalassets;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private long id;

    //the currency name such as gold, coin and so on
    @Column(name = "name")
    private String name;

    //when user create their game account, hou much they can earn
    @Column(name = "initial_count")
    private Integer initialCount;
}
