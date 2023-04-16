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
 * @see Account for user account data
 * @see AccountCurrency for user account currencies
 * @see AccountTest for user account unit tests
 * @see AccountCurrencyTest for user account currency unit tests
 * @see application-dev.yml for logging details
 * @see application-prod.yml for alternate database details
 * @see <a href="https://spring.io/guides/gs/accessing-data-jpa/">Quick Start</a>
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/">Reference</a>
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords">Repository Keywords Guide</a>
 */

package com.group6.project.relational.account;