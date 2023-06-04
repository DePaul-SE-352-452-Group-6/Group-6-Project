package com.group6.project.relational.leaderboards;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Leaderboard {
    @JsonProperty("leaderboard")
    private List<LeaderboardEntry> leaderboard = null;
    @JsonProperty("leaderboard")
    public List<LeaderboardEntry> getLeaderboard(){
        return leaderboard;
    }
    @JsonProperty("leaderboard")
    public void setLeaderboard(List<LeaderboardEntry> entries){
        this.leaderboard = entries;
    }

}
