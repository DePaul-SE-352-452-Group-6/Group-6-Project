package com.group6.project.relational.account;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Account_Currency")
public class AccountCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_id",nullable = false)
    private Integer accountID;

    @Column(name = "currency_id",nullable = false)
    private Integer currencyID;

    @Column(name = "amount",nullable = false)
    private Integer amount;
}