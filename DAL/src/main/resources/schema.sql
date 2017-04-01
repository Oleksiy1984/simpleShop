CREATE TABLE client(
  id INT PRIMARY KEY NOT NULL,
  user_name character varying NOT NULL,
  password character varying NOT NULL,
  role character varying NOT NULL
);
CREATE TABLE manufacturer
(
  id INT PRIMARY KEY NOT NULL,
  name character varying NOT NULL,
);
CREATE TABLE laptop
(
  id INT PRIMARY KEY NOT NULL,
  ram bigint NOT NULL,
  cpu character varying NOT NULL,
  screen character varying NOT NULL,
  image character varying NOT NULL,
  price bigint NOT NULL,
  status boolean NOT NULL,
  quantity bigint NOT NULL,
  id_manufacturer bigint NOT NULL
);
ALTER TABLE laptop
    ADD FOREIGN KEY (id_manufacturer)
    REFERENCES manufacturer(id) ;


