--USE software_eng;

CREATE TABLE users (
 --Increments by one, not secure for logins
 --privateID INT IDENTITY(1,1) PRIMARY KEY,
 --Is a SHA 512 of private ID with salt, can be provided to the user with low risk of collisions or cracking
 --publicID AS HASHBYTES('SHA2_512',CONCAT(CONCAT('-users', privateID), 'software_eng')),
 --Email address provided by user.
 email VARCHAR(128) NOT NULL PRIMARY KEY,
 --Storing SHA512 hash of passwords for security
 passwdHash CHAR(128) NOT NULL,
 --Full name of user, e.g. John Smith.
 fullName VARCHAR(64),
 --Account type: Business owner, employee, or customer. Exists in range [0,255] for forward compatibility
 accountType TINYINT NOT NULL
);

CREATE TABLE businesses (
 --Private ID of the user that controls the business
 ownerID VARCHAR(128) PRIMARY KEY,
 --e.g. ACME inc
 businessName VARCHAR(32),
 --e.g. 123 Pine rd.
 streetAddress VARCHAR(32),
 --Suburb, city, and state are implied by postcode
 postcode INT
);

CREATE TABLE employees (
  --Direct copy of users.privateID
  privateID VARCHAR(128) PRIMARY KEY,
  --businesses.ownerID
  businessID VARCHAR(128)
);

CREATE TABLE postcodes (
  postcode INT,
  --Accounting for Mamungkukumpurangkuntjunya Hill (31 characters long)
  suburbName VARCHAR(32),
  --ACT, NSW, NT, QLD, SA, TAS, VIC, WA
  stateAbbreviation VARCHAR(3)
);

CREATE TABLE bookings (
  --Unique identifier for bookingID, is insecure for showing to users
  privateID INT IDENTITY(1,1) PRIMARY KEY,
  --Unique identifier for bookingID, derived from privateID by way of SHA_512("software_eng[PRIVATE_ID]-bOoKings"), may be shown to end Users
  bookingID AS HASHBYTES('SHA2_512',CONCAT(CONCAT('-bOoKings', privateID), 'software_eng')),
  --Email of customer that made the booking
  customerID VARCHAR(128),
  --Email of business that the booking is made with
  businessID VARCHAR(128),
  --Employee tasked with fulfilling the booking
  employeeID VARCHAR(128),
  --Start of scheduled booking
  startTime INT,
  --End of scheduled booking
  endTime INT,
  --Title of booking, e.g. "Root Canal"
  title VARCHAR(32),
  --Notes made by employee or customer regarding their appointment e.g. "Bring documents x, y, and z"
  notes VARCHAR(255)
);

CREATE TABLE accessibleTimes(
  --KEY
  availabilityID INT IDENTITY(1,1) PRIMARY KEY,
  --Employee or business email
  targetID VARCHAR(128),
  --Day of week the accessibleTime applies to
  dayNum as startTime / 86400,
  --Start Time e.g. 9 am
  startTime INT,
  --end Time
  endTime INT
);
