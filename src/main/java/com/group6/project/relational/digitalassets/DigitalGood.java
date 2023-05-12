package com.group6.project.relational.digitalassets;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "digital_goods")
public class DigitalGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "digital_good_id")
    private long id;

    //digital goods' name such as aroma, weapon and so on
    @Column(name = "name")
    private String name;

    //how much do you need to pay of this good
    @Column(name = "costs")
    private Integer costs;

    //what type of money you use to purchase the digital good
    @Column(name = "currency_id")
    private Integer currencyId;
}
