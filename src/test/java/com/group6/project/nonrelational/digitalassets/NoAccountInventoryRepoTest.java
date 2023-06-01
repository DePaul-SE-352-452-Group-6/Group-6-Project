package com.group6.project.nonrelational.digitalassets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group6.project.relational.digitalassets.NoAccountInventory;
import com.group6.project.relational.digitalassets.NoAccountInventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class NoAccountInventoryRepoTest {
    @Autowired
    private NoAccountInventoryRepository repository;

    @Test
    public void testFindByAccount() {
        // Create test data
        NoAccountInventory inventory1 = new NoAccountInventory("1", "account1", "digitalGood1", "10");
        NoAccountInventory inventory2 = new NoAccountInventory("2", "account2", "digitalGood2", "5");
        repository.save(inventory1);
        repository.save(inventory2);

        // Calling methods for querying
        List<NoAccountInventory> result = repository.findByAccount("account1");

        // Verify query results
        assertEquals(1, result.size());
        assertEquals(inventory1, result.get(0));

        // Clean up test data
        repository.deleteAll();
    }

    @Test
    public void testFindByAccountAndDigitalGood() {
        // Create test data
        NoAccountInventory inventory1 = new NoAccountInventory("1", "account1", "digitalGood1", "10");
        NoAccountInventory inventory2 = new NoAccountInventory("2", "account1", "digitalGood2", "5");
        repository.save(inventory1);
        repository.save(inventory2);

        // Calling methods for querying
        List<NoAccountInventory> result = repository.findByAccountAndDigitalGood("account1", "digitalGood1");

        // Verify query results
        assertEquals(1, result.size());
        assertEquals(inventory1, result.get(0));

        // Clean up test data
        repository.deleteAll();
    }

    @Test
    public void testDeleteByAccount() {
        // Create test data
        NoAccountInventory inventory1 = new NoAccountInventory("1", "account1", "digitalGood1", "10");
        NoAccountInventory inventory2 = new NoAccountInventory("2", "account2", "digitalGood2", "5");
        repository.save(inventory1);
        repository.save(inventory2);

        // Calling methods for querying
        repository.deleteByAccount("account1");

        // Verify query results
        List<NoAccountInventory> remainingInventory = repository.findAll();
        assertEquals(1, remainingInventory.size());
        assertEquals(inventory2, remainingInventory.get(0));

        // Clean up test data
        repository.deleteAll();
    }
}
