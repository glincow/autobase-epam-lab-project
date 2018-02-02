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
INSERT INTO User (name, login, password, role_id) VALUES ('Vasilii Petrov', 'vasia', 'vasia', 3);
INSERT INTO User (name, login, password, role_id) VALUES ('Petr Novikov', 'petia', 'petia', 3);
INSERT INTO User (name, login, password, role_id) VALUES ('Vladimir Putin', 'vova', 'vova', 2);
INSERT INTO User (name, login, password, role_id) VALUES ('John Doe', 'john', 'john', 4);
INSERT INTO User (name, login, password, role_id) VALUES ('Nikolai Ivanov', 'kolia', 'kolia', 3);

INSERT INTO Transport (max_mass, max_volume, isAuto_works, isAuto_available, driver_id) VALUES (5.5, 14.2, TRUE, TRUE, 2);
INSERT INTO Transport (max_mass, max_volume, isAuto_works, isAuto_available, driver_id) VALUES (6.7, 16.5, TRUE, TRUE, 3);
INSERT INTO Transport (max_mass, max_volume, isAuto_works, isAuto_available, driver_id) VALUES (8.3, 20.5, TRUE, TRUE, 6);

INSERT INTO Ride (name, mass, volume, status, customer_id) VALUES ('New York', 1.4, 2.4, 'UNASSIGNED', 5);
INSERT INTO Ride (name, mass, volume, status, customer_id ) VALUES ('Paris', 6.4, 0.3, 'UNASSIGNED', 5);
INSERT INTO Ride (name, mass, volume, status, executor_id, manager_id, customer_id) VALUES ('Berlin', 6.4, 48.3, 'IN_PROCESS', 1, 4, 5);
INSERT INTO Ride (name, mass, volume, status, executor_id, manager_id, customer_id) VALUES ('Novgorod', 4.7, 15.9, 'IN_PROCESS', 2, 4, 5);
INSERT INTO Ride (name, mass, volume, status, executor_id, manager_id, customer_id) VALUES ('Rostov', 8.7, 25.0, 'FINISHED', 1, 4, 5);
INSERT INTO Ride (name, mass, volume, status, executor_id, manager_id, customer_id) VALUES ('Saint-Petersburg', 2.3, 15.4, 'FINISHED', 2, 4, 5);