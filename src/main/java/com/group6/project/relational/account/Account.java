package com.group6.project.relational.account;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group6.project.relational.digitalassets.AccountInventory;
import jakarta.persistence.*;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Additional setup with mapping between code and table/columns when they do not match  class/property
 */

@Data
@Entity
@Builder
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private long ID;

    @Column(name = "user_name",nullable = false,unique = true)
    private String userName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "facebook_id",nullable = true,unique = true)
    private Integer facebookID;

    @Column(name = "apple_id",nullable = true,unique = true)
    private Integer appleID;

    @Column(name = "google_id",nullable = true,unique = true)
    private Integer googleID;

    @Column(name = "ip_address",nullable = true)
    private String ipAddress;

    @Column(name = "country",nullable = true)
    private String country;

    @Column(name = "state",nullable = true)
    private String state;

    @Column(name = "city",nullable = true)
    private String city;

    @Column(name = "signup_date",nullable = false)
    private Date signupDate;

    @Column(name = "last_seen_date",nullable = false)
    private Date lastSeenDate;

    //avoid infinite loop in json
    //instead, if you want to use "GET" command to get the entities on swagger
    //you will get an error
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "currency_id")
    private List<AccountCurrency> currencies;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "digital_good_id")
    private List<AccountInventory> inventory;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> roles;
}
