create database if not exists hotel;
use hotel;

CREATE TABLE IF NOT EXISTS Guests (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NULL
);

CREATE TABLE IF NOT EXISTS Rooms (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    number INT NOT NULL UNIQUE,
    copacity SMALLINT NOT NULL,
    stars SMALLINT NOT NULL,
    price DECIMAL(18 , 4 ),
    is_free BOOLEAN NOT NULL DEFAULT TRUE,
    status ENUM('OPEN', 'CLOSE', 'SERVICED', 'REPAIRABLE') NOT NULL DEFAULT 'open'
);

CREATE TABLE IF NOT EXISTS Options (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(18 , 4 )
);

CREATE TABLE IF NOT EXISTS History (
    id INT NOT NULL AUTO_INCREMENT,
    id_room INT,
    id_guest INT,
    date_of_arival DATE,
    evict_date DATE,
    PRIMARY KEY (id),
    CONSTRAINT fk_guest FOREIGN KEY (id_guest)
        REFERENCES Guests (id),
    CONSTRAINT fk_room FOREIGN KEY (id_room)
        REFERENCES Rooms (id)
);
 
CREATE TABLE IF NOT EXISTS Options_history (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_history INT,
    id_option INT,
    CONSTRAINT fk_history FOREIGN KEY (id_history)
        REFERENCES History (id),
    CONSTRAINT fk_option FOREIGN KEY (id_option)
        REFERENCES Options (id)
);