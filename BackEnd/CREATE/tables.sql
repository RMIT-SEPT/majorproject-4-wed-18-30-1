CREATE DATABASE [software_eng];
USE [software_eng];

CREATE TABLE users (
 --Increments by one, not secure for logins
 privateID INT IDENTITY(1,1) PRIMARY KEY,
 --Is a RSA encryption of private ID with salt, can be provided to the user with low risk of colisions or cracking
 publicID AS POWER((privateID + 1) * 4, 1179) % 9917527,
 --Full name of user, e.g. John Smith.
 fullName VARCHAR(64),
 --Email address provided by user.
 email VARCHAR(64),
 --Account type: Buissness owner, employee, or customer. Exists in range [0,255] for forward compadability
 accountType TINYINT
);

CREATE TABLE businesses (
 buisinessID INT,
 --e.g. ACME inc
 businessName VARCHAR(32),
 --e.g. 123 Pine rd.
 streetAddress VARCHAR(32),
 --Suburb, city, and state are implied by postcode
 postcode INT
);

CREATE TABLE postcodes (
  postcode INT,
  --Accounting for Mamungkukumpurangkuntjunya Hill (31 characters long)
  subsurbName VARCHAR(32),
  --NSW, NT, QLD, TAS, WA
  stateAbreviation VARCHAR(3)
);

CREATE TABLE bookings (
  privateID INT,
  bookingID INT,
  customerID INT,
  businessID INT,
  startTime DATE,
  endTime DATE,
  notes VARCHAR(255)
);
