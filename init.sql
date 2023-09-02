CREATE DATABASE springlab;
USE springlab;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE person(
                       id VARCHAR(255) PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);
INSERT INTO person VALUES('1', 'John Doe');