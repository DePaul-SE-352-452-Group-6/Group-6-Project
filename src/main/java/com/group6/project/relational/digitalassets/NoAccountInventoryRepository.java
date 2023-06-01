package com.group6.project.relational.digitalassets;


import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;


public interface NoAccountInventoryRepository extends MongoRepository<NoAccountInventory, String> {
    List<NoAccountInventory> findByAccount(String account);

    List<NoAccountInventory> findByDigitalGood(String digitalGood);

    List<NoAccountInventory> findByAccountAndDigitalGood(String account, String digitalGood);

    void deleteByAccount(String account);

}
