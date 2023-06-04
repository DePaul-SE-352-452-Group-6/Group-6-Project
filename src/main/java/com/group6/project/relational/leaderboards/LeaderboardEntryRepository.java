package com.group6.project.relational.leaderboards;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaderboardEntryRepository extends JpaRepository<LeaderboardEntry, Long>{
    public LeaderboardEntry findById(long id);

}