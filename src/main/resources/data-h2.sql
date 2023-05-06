insert into account(user_name, password,facebook_id,signup_date,last_seen_date) values ('larry', 'divad',123456789,'2023-01-03','2023-01-03');
insert into account(user_name, password,apple_id,signup_date,last_seen_date) values ('michael', 'divad',987654321,'2022-01-01','2023-01-02');
insert into account(user_name, password,google_id,signup_date,last_seen_date) values ('steven', 'divad',1010101010,'2021-01-01','2023-01-01');

-- insert some data about currencies
insert into currency(name, initial_count) values ('Gems', 500);
insert into currency(name, initial_count) values ('Coin', 2000);
insert into currency(name, initial_count) values ('Gold', 100);

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
insert into account_inventory(inventory_id, account_id, digital_good_id, amount) values (1,1, 1,3);
insert into account_inventory(inventory_id, account_id, digital_good_id, amount) values (2,2, 2,5);
insert into account_inventory(inventory_id,account_id, digital_good_id, amount) values (3,2, 3,1);
insert into account_inventory(inventory_id,account_id, digital_good_id, amount) values (4,3, 4,30);