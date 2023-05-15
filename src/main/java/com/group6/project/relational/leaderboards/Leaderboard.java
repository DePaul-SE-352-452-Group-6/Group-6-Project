package com.group6.project.relational.leaderboards;
import java.sql.Date;
import lombok.Data;
import com.group6.project.relational.account.*;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Leaderboard")
public class Leaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_id")
    private long id;

    @Column(name = "user_name",nullable = false,unique = true)
    private String userName;

    @Column(name = "amount", nullable = false)
    private int amount;

    public Leaderboard(long id, String userName, Integer amount){
        this.id = id;
        this.userName = userName;
        this.amount = amount;
    }

    public Leaderboard(){

    }

    public void setID(int id) {
        this.id = id;
    }
    public void setUserName(String userName){
        this.userName  =  userName;
    }

    public void setAmount(Integer amount){
        this.amount = amount;
    }
}
