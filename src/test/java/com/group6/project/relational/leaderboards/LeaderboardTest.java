package com.group6.project.relational.leaderboards;
import com.group6.project.relational.leaderboards.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardTest {

    @DisplayName("Test Create Leaderboard entry")
    @Test
    public void testLeaderboard(){
        Leaderboard entry = new Leaderboard();
        entry.setID(1);
        entry.setUserName("James");
        entry.setAmount(10000);

        String expectedNoError = "Leaderboard(id=1, userName=James, amount=10000)";
        assertEquals(expectedNoError, entry.toString());
    }

    private LeaderboardRepository repo;

    public void testRepoAdd(){
        Leaderboard entry = new Leaderboard();
        entry.setID(1);
        entry.setUserName("James");
        entry.setAmount(10000);

        var initialRepo = repo.count();
        repo.save(entry);
        var endRepo = repo.count();
        assertEquals(initialRepo + 1, endRepo);

        var findEntry = repo.findById(1);
        assertEquals(entry.getUserName(), "James");

    }

    public void testRepoRemove(){
        var initialRepo = repo.count();
        repo.deleteById(1L);
        var endRepo = repo.count();
        assertEquals(initialRepo, endRepo+1);

        var endCount = repo.count();
        assertEquals(endCount, 0L);
    }
}
