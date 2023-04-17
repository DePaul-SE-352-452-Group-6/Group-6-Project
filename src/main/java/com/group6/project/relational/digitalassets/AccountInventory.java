package com.group6.project.relational.digitalassets;

import jakarta.persistence.*;
import lombok.Data;

//Store the inventory of a user's product
@Data
@Entity
@Table(name = "account_inventory")
public class AccountInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_id",nullable = false)
    private Integer accountID;

    @Column(name = "digital_good_id",nullable = false)
    private Integer digitalGoodID;

    @Column(name = "amount",nullable = false)
    private Integer amount;
}
