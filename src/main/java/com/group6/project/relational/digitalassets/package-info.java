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
 * But in fact, we just need to put or post the new corresponding id to them to  finish this update process
 * instead the whole class information. So, I have to modify the code given by professor in terms of the
 * PUT and POST method in service class.
 *
 *
 * Final Milestone:
 * <li>Added mongodb dependencies to build.gradle and also docker-dbonly.yml, and then created NoAccountInventory compared to relational.AccountInventory.
 *
 * <li>Added NoAccountInventoryRepository, and I have independently created many methods that were not included in the demo code of the class
 *     including findByAccount, findByAccountAndDigitalGood and deleteByAccount and so on.
 *
 * <li>Added NoAccountInventoryService including CRUD operation on swagger
 *
 * <li>Added NoAccountInventoryRepoTest including testFindByAccount, testFindByAccountAndDigitalGood, testDeleteByAccount,
 *     which the codes are totally different from professor's demo code.
 *
 * <li>Added two controllers in terms of CurrenciesController and DigitalGoodsController
 *
 * <li>Added two html files related to CurrenciesController and DigitalGoodsController, there are CurrencyList.html and DigitalGoodList.html
 *     in the package templates.digitalassets. These four classes are related to web-start and front end.
 *
 * <li>Added @BeforeEach and setupAuthentication function to each service tests on digitalassets that are not taught on class.
 *     because of security, and these tests are involved into CRUD operation, we must log in first (not taught in class).
 *
 * <li>Added handleInternalServerError function with annotation @ExceptionHandler on CurrenciesController and DigitalGoodsController
 *     classes to tell the users when their operation may involve cascading relationships or errors, we will directly return to the page
 *     to inform them that cannot make changes, instead of directly displaying 500 messages. (not taught in class).
 *
 * <li>modify data-h2.sql file, because we involved html and relational security, when we want to use username and password to log in
 *     we should use username and password in our database.
 *     However, in our WebSecurityConfig class, we have a method called BCryptPasswordEncoder passwordEncoder.
 *     So we need to encrypt the passwords of each user in our original database in order to access the webpage
 *     with the decoded passwords of the normal username, which were not mentioned in class
 *
 * <li>Added antMatcher and .anyRequest().authenticated() in function filterChain from WebSecurityConfig, which are not taught on class.
 *     antMatcher can make sure both h2 console and swagger are accessible without logging in.
 *     anyRequest(). authenticated() configuration means that any request to enter the application,
 *     whether accessing protected resources or performing protected operations, requires the user to first authenticate.
 *     For example, the digital good and currency HTML we created must be logged in before they can be accessed.
 *     In other words, we use antmatcher and permitAll to create a greenway for our swagger and h2,
 *     but for pages with suffixes other than these, logically, we need to log in to all other access pages.
 *
 *
 * </ol>
 * @see Currency for user currency data including what they are and initial count when they create an account
 * @see DigitalGood for user digital goods data including what they are and the price and what type of currency used to purchase
 * @see CurrenciesTest for Currencies unit tests
 * @see CurrenciesServiceTest for CurrenciesService unit tests
 * @see CurrenciesService for Currencies restful
 * @see CurrenciesRepository for Currencies repo
 * @see DigitalGoodsService for DigitalGoods restful
 * @see DigitalGoodsTest for DigitalGoods unit tests
 * @see DigitalGoodsServiceTest for DigitalGoodsService unit tests
 * @see DigitalGoodsRepository for DigitalGoods repo
 * @see AccountInventory for relational accountInventory
 * @see AccountInventoryService for accountInventory restful
 * @see AccountInventoryTest for AccountInventory unit test
 * @see AccountInventoryServiceTest for AccountInventoryService unit test
 * @see AccountInventoryRequest for helping the RESTFUL operation
 * @see AccountInventoryRepository for accountInventory repo
 * @see CurrenciesController for html delete or modify of Currency
 * @see DigitalGoodsController for html delete or modify of DigitalGood
 * @see NoAccountInventory for mongodb accountInventory
 * @see NoAccountInventoryRepository for mongodb accountInventory repo
 * @see NoAccountInventoryService for mongodb accountInventory restful
 * @see CurrencyList for currency html
 * @see DigitalGoodList for digitalGood html
 * @see NoAccountInventoryRepoTest for mongodb accountInventoryRepo unit tests
 * @see application-dev.yml for logging details and mongodb details
 * @see application-prod.yml for alternate database details and mongodb details
 * @see <a href="https://spring.io/guides/gs/accessing-data-jpa/">Quick Start</a>
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/">Reference</a>
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords">Repository Keywords Guide</a>
 * @author Zhihong HE
 */
package com.group6.project.relational.digitalassets;

