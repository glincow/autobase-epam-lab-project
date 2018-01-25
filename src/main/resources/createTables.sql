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
  customer_id BIGINT,
  FOREIGN KEY(executor_id) REFERENCES Transport(id),
  FOREIGN KEY(manager_id) REFERENCES User(id),
  FOREIGN KEY(customer_id) REFERENCES User(id)
);

-- Order of inserts matters because using auto_increment in key columns

INSERT INTO Role SET name= 'Admin';
INSERT INTO Role SET name= 'Manager';
INSERT INTO Role SET name= 'Driver';
INSERT INTO Role SET name= 'Customer';

INSERT INTO User (name, login, password, role_id) VALUES ('Admin', 'admin', 'admin', 1);
INSERT INTO User (name, login, password, role_id) VALUES ('Vasia', 'vasia', 'vasia', 3);
INSERT INTO User (name, login, password, role_id) VALUES ('Petia', 'petia', 'petia', 3);
INSERT INTO User (name, login, password, role_id) VALUES ('Vova', 'vova', 'vova', 2);

INSERT INTO Transport (max_mass, max_volume, isAuto_works, isAuto_available, driver_id) VALUES (12.4, 14.2, TRUE, TRUE, 2);
INSERT INTO Transport (max_mass, max_volume, isAuto_works, isAuto_available, driver_id) VALUES (27.0, 16.5, TRUE, TRUE, 3);

INSERT INTO Ride (name, mass, volume, status) VALUES ('WestRide', 1.4, 2.4, 'unassigned');
INSERT INTO Ride (name, mass, volume, status) VALUES ('EastRide', 6.4, 0.3, 'unassigned');
INSERT INTO Ride (name, mass, volume, status, executor_id, manager_id) VALUES ('SouthRide', 6.4, 0.3, 'IN_PROCESS', 1, 4);
INSERT INTO Ride (name, mass, volume, status, executor_id, manager_id) VALUES ('NorthRide', 24.7, 15.9, 'IN_PROCESS', 2, 4);
INSERT INTO Ride (name, mass, volume, status, executor_id, manager_id) VALUES ('NERide', 8.7, 5.9, 'finished', 1, 4);
INSERT INTO Ride (name, mass, volume, status, executor_id, manager_id) VALUES ('SERide', 4.7, 15.9, 'finished', 2, 4);