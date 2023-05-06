package com.group6.project.relational.digitalassets;

import com.group6.project.relational.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

//Store the inventory of a user's product
@Data
@Entity
@Table(name = "account_inventory")
public class AccountInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column(name = "account_id",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Account account;

    @Column(name = "digital_good_id",nullable = false)
    private Integer digitalGoodID;

    @Column(name = "amount",nullable = false)
    private Integer amount;
}
