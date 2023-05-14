package com.group6.project.relational.digitalassets;

import lombok.Builder;
import lombok.Data;

//This is an auxiliary class for account inventory, we can avoid inserting whole account and digital info
//when we use swagger
@Data
//@Builder
public class AccountInventoryRequest {
    private long id;
    private long accountId;
    private long digitalGoodID;
    private Integer amount;

    public AccountInventoryRequest(long id, long accountId, long digitalGoodID, Integer amount) {
        this.id = id;
        this.accountId = accountId;
        this.digitalGoodID = digitalGoodID;
        this.amount = amount;
    }
}
