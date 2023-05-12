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
    @Column(name = "inventory_id")
    private long id;

    //one account can have multiple inventories
    //such as account_number 1, that guy can have weapon(digital_good_id=1), shoes(digital_good_id=2) and so on
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "digital_good_id")
    private DigitalGood digitalGoodID;

    @Column(name = "amount",nullable = false)
    private Integer amount;
}
