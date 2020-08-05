-- Create a new stored procedure called 'CreateEmployee' in schema 'software_eng'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
  FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_NAME = N'CreateEmployee'
--  AND SPECIFIC_SCHEMA = N'software_eng' 
)
DROP PROCEDURE CreateEmployee
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE CreateEmployee
  @EMAIL      VARCHAR(128) = '', 
  @HASH       CHAR(128)    = '', 
  @FULLNAME   VARCHAR(64)  = '',
  @BUSINESSID INT          = 0

-- add more stored procedure parameters here
AS
  -- body of the stored procedure
  INSERT INTO users (passwdHash, fullName, email, accountType)
  VALUES (@HASH, @FULLNAME, @EMAIL, 1);

  --Get assigned private ID for linking to managing business
  DECLARE @PRIVATEID VARBINARY ;
  SET @PRIVATEID = (SELECT TOP 1 privateID FROM users
                    WHERE passwdHash = @HASH
                    AND email = @EMAIL);

  INSERT INTO employees
  VALUES (@PRIVATEID, @BUSINESSID);
GO
-- example to execute the stored procedure we just created

DECLARE @BUSINESSID VARBINARY; 
SET @BUSINESSID = (SELECT TOP 1 publicID FROM users
                        WHERE accountType = 0);

EXECUTE CreateEmployee 'nobody@nowhere.com' , 'cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e' /*Hash of empty string ('')*/, 'Jane Doe', @BUSINESSID
GO