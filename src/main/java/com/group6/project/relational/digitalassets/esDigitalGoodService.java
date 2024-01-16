package com.group6.project.relational.digitalassets;


import java.io.IOException;
import java.util.List;


public interface esDigitalGoodService {
    DigitalGood createDigitalGood(DigitalGood digitalGood) throws IOException;

    Iterable<DigitalGood> getAllDigitalGoods() throws IOException;

    DigitalGood getDigitalGoodById(Long id) throws IOException;

    DigitalGood updateDigitalGood(Long id, DigitalGood digitalGood) throws IOException;

    boolean deleteDigitalGood(Long id) throws IOException;

    List<DigitalGood> getDigitalGoodsByCurrency(Integer currencyId) throws IOException;

    List<DigitalGood> searchDigitalGoodsByCostRange(Integer minCost, Integer maxCost) throws IOException;

    // 这里可以添加更多的搜索方法，比如基于名称或者其他属性的搜索
    List<DigitalGood> searchDigitalGoodsByName(String name) throws IOException;

}
