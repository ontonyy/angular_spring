--liquibase formatted sql

--changeset AntonGavrilin:3:createHeroes
CREATE TABLE IF NOT EXISTS heroes(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    power INT NOT NULL
)