package com.group6.project.concepts.relational.basic;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * Additional setup with mapping between code and table/columns when they do not match  class/property
 */
@Data
@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "stu_id")
    private String studentId;

    @Column(name = "nm")
    private String name;

    private String email;

    private long age;

    private Date admittedDate;
}