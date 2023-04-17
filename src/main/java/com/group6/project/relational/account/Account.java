package com.group6.project.relational.account;

import java.sql.Date;

import jakarta.persistence.*;

import lombok.Data;

/**
 * Additional setup with mapping between code and table/columns when they do not match  class/property
 */

@Data
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
}