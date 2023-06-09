-- because in WebSecurityConfig, we have this method (BCryptPasswordEncoder passwordEncoder())
-- This means that the passwords of each user we save to the database are encrypted.
-- If we want to access the login page with the username 'larry' and password 'divad',
-- we need to use the following website for encryption in order to achieve our goal
-- https://bcrypt-generator.com/

-- in conclusion, we can use following username and password to login
-- (larry, divad)  (michael, divad)  (steven, divad)
insert into account(user_name, password,facebook_id,signup_date,last_seen_date) values ('larry', '$2a$12$Fo4xdMlzz01MyODHw6x6.ODI3wlThVo0rdaNQlSLWziwrk36DwT82',123456789,'2023-01-03','2023-01-03');
insert into account(user_name, password,apple_id,signup_date,last_seen_date) values ('michael', '$2a$12$Fo4xdMlzz01MyODHw6x6.ODI3wlThVo0rdaNQlSLWziwrk36DwT82',987654321,'2022-01-01','2023-01-02');
insert into account(user_name, password,google_id,signup_date,last_seen_date) values ('steven', '$2a$12$Fo4xdMlzz01MyODHw6x6.ODI3wlThVo0rdaNQlSLWziwrk36DwT82',1010101010,'2021-01-01','2023-01-01');

-- insert some data about currencies
insert into currency(name, initial_count) values ('Gems', 500);
insert into currency(name, initial_count) values ('Coin', 2000);
insert into currency(name, initial_count) values ('Gold', 100);
insert into currency(name, initial_count) values ('stone', 1000);

insert into account_currency(account_id,currency_id,amount) values (1,1,500);
insert into account_currency(account_id,currency_id,amount) values (2,1,500);
insert into account_currency(account_id,currency_id,amount) values (2,2,300);
insert into account_currency(account_id,currency_id,amount) values (3,3,150);

-- insert some data about digital goods
-- 1 for gem; 2 for coin; 3 for gold
insert into digital_goods(name, costs, currency_id) values ('weapon', 500, 1);
insert into digital_goods(name, costs, currency_id) values ('armor', 200, 1);
insert into digital_goods(name, costs, currency_id) values ('shoe', 100, 1);
insert into digital_goods(name, costs, currency_id) values ('herbs', 50, 2);
insert into digital_goods(name, costs, currency_id) values ('key_item_1', 1000, 3);

--insert some data in our inventory
insert into account_inventory(account_id, digital_good_id, amount) values (1, 1,3);
insert into account_inventory(account_id, digital_good_id, amount) values (2, 2,5);
insert into account_inventory(account_id, digital_good_id, amount) values (2, 4,1);
insert into account_inventory(account_id, digital_good_id, amount) values (3, 1,10);

--insert leaderboard entry data
insert into leaderboard(id, amount, user_name) values (123, 10000, 'James');
insert into leaderboard(id, amount, user_name) values (124, 20000, 'Daniel');
insert into leaderboard(id, amount, user_name) values (125, 30000, 'Emma');
insert into leaderboard(id, amount, user_name) values (126, 40000, 'Ryan');

INSERT INTO user_roles(name) VALUES('ROLE_USER');
INSERT INTO user_roles(name) VALUES('ROLE_ADMIN');