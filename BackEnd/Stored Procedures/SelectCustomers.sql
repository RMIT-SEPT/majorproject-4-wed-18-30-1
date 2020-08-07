-- Create a new stored procedure called 'SelectUsers' in schema ''
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
  FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_NAME = N'SelectUsers'
)
DROP PROCEDURE SelectUsers
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE SelectUsers
-- add more stored procedure parameters here
AS
  -- body of the stored procedure
  SELECT u.publicID, u.fullName, u.email, u.passwdHash FROM users
  WHERE accountType = 2
GO
-- example to execute the stored procedure we just created
EXECUTE SelectUsers 1 /*value_for_param1*/, 2 /*value_for_param2*/
GO