package com.group6.project.concepts.relational.basic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Example of adding additional finders
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.repositories">Repository</a>
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByAgeLessThanEqual(long age);

    public List<Student> findByName(String nm);

    public List<Student> findByNameLike(String nm);
}