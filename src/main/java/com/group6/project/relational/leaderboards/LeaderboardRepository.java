package com.group6.project.relational.leaderboards;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long>{
    public List<Leaderboard> createLeaderboard();
    public Leaderboard findById(long id);
    public void addUser(Leaderboard entry);
    public void removeUser(Leaderboard entry);
    public void updateUser(Leaderboard entry, int amount);
    public void updateUser(long id, int amount);
}
