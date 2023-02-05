--liquibase formatted sql

--changeset AntonGavrilin:2:addHeroes
INSERT INTO heroes(name, power)
VALUES('Dr. Nice', 100),
      ('Bombasto', 110),
      ('Celeritas', 120),
      ('Magneta', 130),
      ('RubberMan', 140),
      ('Dynama', 150),
      ('Dr. IQ', 160),
      ('Magma', 170),
      ('Tornado', 180);
