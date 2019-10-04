CREATE SCHEMA stolen_cars;
USE stolen_cars;

CREATE TABLE IF NOT EXISTS car (
    car_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id VARCHAR(127) NOT NULL,
    ovd VARCHAR(127),
    brand VARCHAR(127),
    color VARCHAR(127),
    vehiclenumber VARCHAR(127),
    bodynumber VARCHAR(127),
    chassisnumber VARCHAR(127),
    enginenumber VARCHAR(127),
    theft_data DATETIME,
    insert_date DATETIME,
    PRIMARY KEY (car_id),
    UNIQUE KEY carid_unique (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS dataset (
    datasetid INT UNSIGNED NOT NULL AUTO_INCREMENT,
    created DATETIME,
    url VARCHAR(500),
    carscount INTEGER,
    updated DATETIME,
    PRIMARY KEY (datasetid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    userid INT,
    name VARCHAR(127),
    username VARCHAR(127),
    isremembered TINYINT,
    regdate DATETIME,
    chatid LONG,
    PRIMARY KEY (id),
    UNIQUE KEY USERID_UNIQUE (userid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS message (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    userid INT,
    messagescount INT,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS car_info(
    carinfo_id INTEGER UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    reg_addr_koatuu VARCHAR(200),
    operation_code VARCHAR(20),
    operation_name VARCHAR(200),
    regdate DATE,
    department_code VARCHAR(20),
    brand VARCHAR(200),
    model VARCHAR(200),
    make_year YEAR,
    color VARCHAR(40),
    kind VARCHAR(40),
    body VARCHAR(100),
    fuel VARCHAR(40),
    capacity MEDIUMINT DEFAULT NULL,
    own_weight MEDIUMINT,
    total_weight MEDIUMINT,
    vehicle_number VARCHAR(100),
    PRIMARY KEY (carinfo_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;