BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS markers;

CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user')
);

CREATE TABLE markers (
    id serial PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    username varchar(255) REFERENCES users(username),
    report_date timestamp DEFAULT CURRENT_TIMESTAMP,
    repair_date timestamp DEFAULT NULL,
    latitude varchar(64) NOT NULL,
    longitude varchar(64) NOT NULL,
    rating varchar(16) NOT NULL,
    comments varchar(256) DEFAULT ''
);

COMMIT TRANSACTION;
