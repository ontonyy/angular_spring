--liquibase formatted sql

--changeset AntonGavrilin:3:createUsers
CREATE TABLE IF NOT EXISTS users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    power INT NOT NULL
)