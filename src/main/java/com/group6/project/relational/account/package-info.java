/**
 * Base Account Data for users
 * Managed by Cameron Cintron
 *
 * SELECT * FROM ACCOUNT_CURRENCY as ac,ACCOUNT as a where a.id = ac.account_id to join account to accountcurrency
 *
 * <ol>
 * <li>Created Account and AccountCurrent Repository for Milestone 2
 * <li>Created AccountTest and AccountCurrencyTest for Milestone 2
 * <li>Added a OneToOne relationship from AccountCurrency to Currencies
 * <li>Added a OneToMany relationship from Account to AccountCurrency
 * <li>Added a OneToMany relationship from Account to AccountInventory
 * <li>Added a Account Service
 * <li>Added a AccountCurrency Service
 * <li>Integrated swagger into project
 * <li>Added nullable and unique elements to generatedvalue on columns not discussed in class
 * </ol>
 * @see Account for user account data
 * @see AccountCurrency for user account currencies
 * @see AccountTest for user account unit tests
 * @see AccountCurrencyTest for user account currency unit tests
 * @see application-dev.yml for logging details
 * @see application-prod.yml for alternate database details
 * @see <a href="https://spring.io/guides/gs/accessing-data-jpa/">Quick Start</a>
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/">Reference</a>
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords">Repository Keywords Guide</a>
 * @author Cameron Cintron
 */

package com.group6.project.relational.account;