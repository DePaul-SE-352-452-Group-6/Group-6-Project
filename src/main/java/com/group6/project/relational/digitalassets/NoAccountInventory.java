package com.group6.project.relational.digitalassets;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class NoAccountInventory {
    @Id
    private String id;

    private String account;

    private String digitalGood;

    private String amount;

}
