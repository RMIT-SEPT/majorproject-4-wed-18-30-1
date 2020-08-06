-- Create a new stored procedure called 'LoginUser' in schema 'software_eng'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
  FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_NAME = N'LoginUser'
)
DROP PROCEDURE LoginUser
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE LoginUser
  @HASH /*parameter name*/ CHAR(128) /*datatype_for_param1*/ = "empty", /*default_value_for_param1*/
  @EMAIL /*parameter name*/ VARCHAR(128) /*datatype_for_param1*/ = "empty" /*default_value_for_param2*/
-- add more stored procedure parameters here
AS
  -- body of the stored procedure
  SELECT TOP 1 publicID FROM users
  WHERE passwdHash = @HASH
  AND email = @EMAIL;
GO
-- example to execute the stored procedure we just created
EXECUTE LoginUser "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e", "nobody@nowhere.com"
GO