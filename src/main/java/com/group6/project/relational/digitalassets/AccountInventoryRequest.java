package com.group6.project.relational.digitalassets;

import lombok.Data;

//This is an auxiliary class for account inventory, we can avoid inserting whole account and digital info
//when we use swagger
@Data
public class AccountInventoryRequest {
    private long id;
    private long accountId;
    private long digitalGoodID;
    private Integer amount;
}
