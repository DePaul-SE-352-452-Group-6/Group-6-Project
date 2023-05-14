package com.group6.project.relational.digitalassets;

import com.group6.project.relational.account.Account;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

//Store the inventory of a user's product
@Data
@Entity
@Table(name = "Account_Inventory")
public class AccountInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_inventory_id")
    private long ID;

    //one account can have multiple inventories
    //such as account_number 1, that guy can have weapon(digital_good_id=1), shoes(digital_good_id=2) and so on
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "digital_good_id")
    private DigitalGood digitalGood;

    @Column(name = "amount",nullable = false)
    private Integer amount;

    public AccountInventory() {
    }

    public AccountInventory(long ID, Account account, DigitalGood digitalGood, Integer amount) {
        this.ID = ID;
        this.account = account;
        this.digitalGood = digitalGood;
        this.amount = amount;
    }
}
