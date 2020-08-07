-- Create a new stored procedure called 'SelectBusinesses' in schema  
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
  FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_NAME = N'SelectBusinesses'
)
DROP PROCEDURE SelectBusinesses
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE SelectBusinesses
-- add more stored procedure parameters here
AS
  -- body of the stored procedure

  --Should return up to 7 instances of each business, one for each day they are open
  SELECT u.name, u.email, u.passwdHash, b.streetAddress, b.postCode, a.dayOfWeek, a.startTime, a.endTime FROM users as u, businesses as b, postcodes as p, accessibleTimes as a
  WHERE u.accountType = 0
  AND u.privateID = b.ownerID
  AND p.postcode = b.postcode
  AND a.targetID = b.ownerID
GO
-- example to execute the stored procedure we just created
EXECUTE SelectBusinesses 1 /*value_for_param1*/, 2 /*value_for_param2*/
GO