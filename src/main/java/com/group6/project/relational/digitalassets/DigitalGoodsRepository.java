package com.group6.project.relational.digitalassets;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DigitalGoodsRepository extends JpaRepository<DigitalGood, Long> {

    public DigitalGood findById(long id);
    public DigitalGood findByName(String name);

    //If you want to find which digital goods can be purchased through specific currency types,
    //you should return a list at this time
    //For example, gold can buy weapons as well as shoes
    public List<DigitalGood> findByCurrencyId(Integer CurrencyId);
}
