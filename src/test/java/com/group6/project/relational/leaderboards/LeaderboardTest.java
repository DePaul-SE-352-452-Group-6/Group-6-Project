package com.group6.project.relational.leaderboards;
import com.group6.project.relational.leaderboards.*;
import com.group6.project.relational.digitalassets.Currency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardTest {

    @DisplayName("Test Create Account")
    @Test
    public void testLeaderboard(){
        Leaderboard entry = new Leaderboard();
        entry.setID(1);
        entry.setUserName("James");
        entry.setAmount(10000);

        String expectedNoError = "Leaderboard(id=1, userName=James, amount=10000)";
        assertEquals(expectedNoError, entry.toString());
    }
}
