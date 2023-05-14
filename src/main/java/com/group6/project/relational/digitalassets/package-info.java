/**
 * Base digital goods and currencies information
 * Managed by Zhihong HE
 *
 * not discussed in class
 *
 * SELECT *  FROM account_inventory, Account
 * WHERE account_inventory.account_id = Account.id
 * In order to better understand the detailed ownership of digital products for each account
 *
 * <ol>
 * Milestone2:
 * <li>Created AccountInventory, Currency as well as Digital Good for Milestone 2
 * <li>Created AccountInventory repository, Currency repository as well as Digital Good repository for Milestone 2
 * <li>Created AccountInventoryTest and CurrenciesTest and DigitalGoodsTest for Milestone 2
 *
 * Milestone3:
 * <li>Created AccountInventoryServiceTest and CurrenciesServiceTest and DigitalGoodsServiceTest for Milestone 3
 * <li>Added a ManyToOne relationship from AccountInventory to Account
 * <li>Added a ManyToOne relationship from AccountInventory to Digital Good
 * <li>Added a AccountInventory Service and Swagger Documentation
 * <li>Added a Currency Service and Swagger Documentation
 * <li>Added a Digital Good Service and Swagger Documentation
 * <li>Integrated swagger into project
 * <li>Added nullable on columns in AccountInventory class and added @JsonIgnore on account class to avoid infinite loop in json when we use swagger,
 * which were not discuss on the class
 * <li>Added accountInventoryRequest class to auxiliary AccountInventoryService class, if don't do that. we will
 * encounter the problem that we need to insert the whole information about the account and digital good.
 * But in fact, we just need to put or post the new corresponding id to them to finish this update process
 * instead the whole class information. So, I have to modify the code given by professor in terms of the
 * PUT and POST method in service class.
 *
 * </ol>
 * @see Currency for user currency data including what they are and initial count when they create an account
 * @see DigitalGood for user digital goods data including what they are and the price and what type of currency used to purchase
 * @see CurrenciesTest for Currencies unit tests
 * @see DigitalGoodsTest for DigitalGoods unit tests
 * @see AccountInventoryTest for AccountInventory unit test
 * @see application-dev.yml for logging details
 * @see application-prod.yml for alternate database details
 * @see <a href="https://spring.io/guides/gs/accessing-data-jpa/">Quick Start</a>
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/">Reference</a>
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords">Repository Keywords Guide</a>
 * @author Zhihong HE
 */
package com.group6.project.relational.digitalassets;

