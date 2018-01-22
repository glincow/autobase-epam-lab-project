DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Transport;
DROP TABLE IF EXISTS Ride;

CREATE TABLE Role(
  id INT auto_increment PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE User(
  id INT auto_increment PRIMARY KEY,
  name VARCHAR(255),
  login VARCHAR(255),
  password VARCHAR(255),
  role INT,
  FOREIGN KEY(role) REFERENCES role(ID)
);

CREATE TABLE Transport(
  id INT auto_increment PRIMARY KEY,
  param_max_mass FLOAT,
  param_max_volume FLOAT,
  auto_works BOOLEAN,
  auto_available BOOLEAN,
  driver INT,
  FOREIGN KEY(driver) REFERENCES User(ID)
);

CREATE TABLE Ride(
  id INT auto_increment PRIMARY KEY,
  name VARCHAR(255),
  param_mass FLOAT,
  param_volume FLOAT,
  status VARCHAR(255),
  executor_ID INT,
  manager_ID INT,
  FOREIGN KEY(executor_ID) REFERENCES Transport(ID),
  FOREIGN KEY(manager_ID) REFERENCES User(ID)
);