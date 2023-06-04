package com.group6.project.relational.leaderboards;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.DisplayName;


@SpringBootTest
public class LeaderboardEntryTest {

    @DisplayName("Test Create Leaderboard Entry")
    @Test
    public void testLeaderboardEntry(){
        LeaderboardEntry entry = new LeaderboardEntry();
        entry.setId(123);
        entry.setUserName("Joe");
        entry.setAmount(14000);

        String expected = "Leaderboard(123, Joe, 14000)";
        assertEquals(expected, entry.toString());
    }

    @Autowired
    private LeaderboardEntryRepository leaderboardEntryRepository;

    @DisplayName("Test Adding Leaderboard Entry")
    @Test
    public void testAddingLeaderboardEntry() {
        LeaderboardEntry entry = new LeaderboardEntry();
        entry.setId(123);
        entry.setUserName("Joe");
        entry.setAmount(14000);

        var initialEntries = leaderboardEntryRepository.count();
        leaderboardEntryRepository.save(entry);
        var afterAdd = leaderboardEntryRepository.count();

        assertEquals(initialEntries + 1, afterAdd);

        var foundAccount = leaderboardEntryRepository.findById(123);
        assertEquals("Joe", foundAccount.getUserName());
    }
}