-- Create a new stored procedure called 'SelectEmployees' in schema ''
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
  FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_NAME = N'SelectEmployees'
)
DROP PROCEDURE SelectEmployees
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE SelectEmployees
-- add more stored procedure parameters here
AS
  -- body of the stored procedure
  SELECT u.fullName, u.email, u.passwdHash, b.ownerID, b.businessName, b.streetAddress, b.postcode FROM users AS u, businesses AS b, employees AS e
  WHERE accountType = 2
  AND e.privateID = u.privateID
  AND b.businessID = e.businessID
GO
-- example to execute the stored procedure we just created
EXECUTE SelectEmployees
GO