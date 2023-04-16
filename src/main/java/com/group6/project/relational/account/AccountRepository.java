package com.group6.project.relational.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

/**
 * Example of adding additional finders
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.repositories">Repository</a>
 */
public interface AccountRepository extends JpaRepository<Account, Long> {


    public Account findByUserName(String userName);

    public Account findByFacebookID(Integer id);

    public Account findByGoogleID(Integer id);

    public Account findByAppleID(Integer id);

    public List<Account> findByLastSeenDateLessThanEqual(Date date);

    public List<Account> findByLastSeenDateGreaterThanEqual(Date date);

    public List<Account> findBySignupDateLessThanEqual(Date date);

    public List<Account> findBySignupDateGreaterThanEqual(Date date);

    public List<Account> findByCountry(String country);

    public List<Account> findByCity(String city);

    public List<Account> findByState(String state);

    /*public List<Student> findByName(String nm);

    public List<Student> findByNameLike(String nm);*/

}