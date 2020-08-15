-- Create a new stored procedure called 'SelectEmployee' in schema ''
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
  FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_NAME = N'SelectEmployee'
)
DROP PROCEDURE SelectEmployee
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE SelectEmployee
  @PUBLIC_ID VARBINARY = 0
-- add more stored procedure parameters here
AS
  -- body of the stored procedure
  SELECT u.publicID, u.fullName, u.email, b.businessName, b.streetAddress, b.postcode FROM users AS u, businesses AS b, employees AS e
  WHERE u.publicID = @PUBLIC_ID
  AND e.privateID = u.privateID
  AND b.businessID = e.businessID
GO
-- example to execute the stored procedure we just created
EXECUTE SelectEmployee 1 /*value_for_param1*/, 2 /*value_for_param2*/
GO