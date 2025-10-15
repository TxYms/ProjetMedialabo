CREATE DATABASE IF NOT EXISTS medialabo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'medialabo'@'%' IDENTIFIED BY 'medialabo';
GRANT ALL PRIVILEGES ON medialabo.* TO 'medialabo'@'%';
FLUSH PRIVILEGES;

USE medialabo;

CREATE TABLE IF NOT EXISTS patients (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name  VARCHAR(80)  NOT NULL,
  last_name   VARCHAR(80)  NOT NULL,
  birth_date  DATE         NOT NULL,
  sex         CHAR(1)      NOT NULL CHECK (sex IN ('M','F')),
  address     VARCHAR(255) NOT NULL,
  phone       VARCHAR(32)  NOT NULL
);

CREATE INDEX idx_patients_last_first ON patients(last_name, first_name);

INSERT INTO patients(first_name, last_name, birth_date, sex, address, phone) VALUES
('TestNone',        'Test', '1966-12-31', 'F', '1 Brookside St', '100-222-3333'),
('TestBorderline',  'Test', '1945-06-24', 'M', '2 High St',      '200-333-4444'),
('TestInDanger',    'Test', '2004-06-18', 'M', '3 Club Road',    '300-444-5555'),
('TestEarlyOnset',  'Test', '2002-06-28', 'F', '4 Valley Dr',    '400-555-6666');

