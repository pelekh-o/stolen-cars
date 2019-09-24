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
    filesize INTEGER,
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
)