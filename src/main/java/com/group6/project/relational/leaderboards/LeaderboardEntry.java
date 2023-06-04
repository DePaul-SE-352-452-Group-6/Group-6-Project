package com.group6.project.relational.leaderboards;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "leaderboard")
public class LeaderboardEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_name",nullable = false,unique = true)
    private String userName;

    @Column(name = "amount", nullable = false)
    private int amount;


}