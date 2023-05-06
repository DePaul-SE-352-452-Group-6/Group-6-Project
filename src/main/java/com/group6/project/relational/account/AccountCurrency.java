package com.group6.project.relational.account;

import com.group6.project.relational.digitalassets.Currencies;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Account_Currency")
public class AccountCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    //@Column(name = "account_id",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Account account;

    //@Column(name = "currency_id",nullable = false)
    @OneToOne
    @JoinColumn(name = "currency_id")
    private Currencies currencyID;

    @Column(name = "amount",nullable = false)
    private Integer amount;
}