DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS account_currency;
DROP TABLE IF EXISTS currency;
DROP TABLE IF EXISTS digital_goods;
DROP TABLE IF EXISTS account_inventory;

CREATE TABLE account(
    user_name VARCHAR(50),
    password VARCHAR(50),
    facebook_id INTEGER(20),
    signup_date VARCHAR(50),
    last_seen_date VARCHAR(50)
);

CREATE TABLE account(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50),
    password VARCHAR(50),
    apple_id INTEGER(20),
    signup_date VARCHAR(50),
    last_seen_date VARCHAR(50)
);

CREATE TABLE account(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50),
    password VARCHAR(50),
    google_id INTEGER(20),
    signup_date VARCHAR(50),
    last_seen_date VARCHAR(50)
);

CREATE TABLE account_currency(
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INTEGER(20),
    currency_id INTEGER(20),
    amount INTEGER(20)
);

CREATE TABLE currency(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    initial_count INTEGER(50)
);

CREATE TABLE digital_goods(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    costs INTEGER(20),
    currency_id INTEGER(20)
);

CREATE TABLE account_inventory(
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INTEGER(20),
    digital_good_id INTEGER(20),
    amount INTEGER(20)
);