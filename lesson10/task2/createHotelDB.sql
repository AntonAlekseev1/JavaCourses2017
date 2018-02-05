create database if not exists hotel;
use hotel;

CREATE TABLE IF NOT EXISTS Guests (
    id_guest INT NOT NULL PRIMARY KEY,
    name VARCHAR(50),
    last_name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Rooms (
    id_room INT NOT NULL PRIMARY KEY,
    copaciti SMALLINT,
    stars VARCHAR(5),
    price DECIMAL(18 , 4 ),
    is_free CHAR(1),
    status VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS Services (
    id_service INT NOT NULL PRIMARY KEY,
    name VARCHAR(50),
    price DECIMAL(18 , 4 )
);

CREATE TABLE IF NOT EXISTS History (
  id INT NOT NULL,
  id_room INT,
  id_guest INT,
  id_service INT,
  date_of_arival DATE,
  evict_date DATE,
  PRIMARY KEY (id),
  CONSTRAINT id_guest
    FOREIGN KEY (id_guest)
    REFERENCES Guests (id_guest),
  CONSTRAINT id_room
    FOREIGN KEY (id_room)
    REFERENCES Rooms (id_room),
  CONSTRAINT id_service
    FOREIGN KEY (id_service)
    REFERENCES Services (id_service)
 );