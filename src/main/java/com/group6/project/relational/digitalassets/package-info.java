/**
 * Base Account Data for users
 * Managed by Cameron Cintron
 *
 * Added nullable and unique elements to generatedvalue on columns
 * not discussed in class
 *
 * SELECT * FROM ACCOUNT_CURRENCY as ac,ACCOUNT as a where a.id = ac.account_id to join account to accountcurrency
 *
 * <ol>
 * <li>build.gradle that define JPA and
 * </ol>
 * @see Currencies for user currency data including what they are and initial count when they create an account
 * @see DigitalGoods for user digital goods data including what they are and the price and what type of currency used to purchase
 * @see CurrenciesTest for Currencies unit tests
 * @see DigitalGoodsTest for DigitalGoods unit tests
 * @see AccountInventoryTest for AccountInventory unit test
 * @see application-dev.yml for logging details
 * @see application-prod.yml for alternate database details
 * @see <a href="https://spring.io/guides/gs/accessing-data-jpa/">Quick Start</a>
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/">Reference</a>
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords">Repository Keywords Guide</a>
 */
package com.group6.project.relational.digitalassets;