DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Transport;
DROP TABLE IF EXISTS Ride;

CREATE TABLE Role(
  id BIGINT auto_increment PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE User(
  id BIGINT auto_increment PRIMARY KEY,
  name VARCHAR(255),
  login VARCHAR(255),
  password VARCHAR(255),
  role_id BIGINT,
  FOREIGN KEY(role_id) REFERENCES role(id)
);

CREATE TABLE Transport(
  id BIGINT auto_increment PRIMARY KEY,
  max_mass FLOAT,
  max_volume FLOAT,
  isAuto_works BOOLEAN,
  isAuto_available BOOLEAN,
  driver_id BIGINT,
  FOREIGN KEY(driver_id) REFERENCES User(id)
);

CREATE TABLE Ride(
  id BIGINT auto_increment PRIMARY KEY,
  name VARCHAR(255),
  mass FLOAT,
  volume FLOAT,
  status VARCHAR(255),
  executor_id BIGINT,
  manager_id BIGINT,
  FOREIGN KEY(executor_id) REFERENCES Transport(id),
  FOREIGN KEY(manager_id) REFERENCES User(id)
);

-- Order of inserts matters because using auto_increment in key columns

INSERT INTO Role SET name= 'Admin';
INSERT INTO Role SET name= 'Manager';
INSERT INTO Role SET name= 'Driver';
INSERT INTO Role SET name= 'Customer';

INSERT INTO User (name, login, password, role_id) VALUES ('Admin', 'admin', 'admin', 1);
INSERT INTO User (name, login, password, role_id) VALUES ('Manager', 'man', 'man', 2);
INSERT INTO User (name, login, password, role_id) VALUES ('Driver', 'dr', 'dr', 3);
INSERT INTO User (name, login, password, role_id) VALUES ('Customer', 'cus', 'cus', 4);
