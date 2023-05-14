package com.group6.project.relational.account;

import com.group6.project.relational.digitalassets.Currency;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Account_Currency")
public class AccountCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_currency_id")
    private long ID;

    //@Column(name = "account_id",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinColumn(name = "account_id")
    private Account account;

    //@Column(name = "currency_id",nullable = false)
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currencyID;

    @Column(name = "amount",nullable = false)
    private Integer amount;
}