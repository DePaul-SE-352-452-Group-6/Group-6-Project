package com.group6.project.relational.digitalassets;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DigitalGoodsRepository extends JpaRepository<DigitalGoods, Long> {
    public DigitalGoods findByName(String name);
    public DigitalGoods findByCosts(Integer costs);
    public DigitalGoods findByCurrencyId(Integer CurrencyId);
}
