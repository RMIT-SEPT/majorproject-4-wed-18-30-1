-- Create a new stored procedure called 'CreateCustomer' in schema 'software_eng'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
  FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_NAME = N'CreateCustomer'
-- AND SPECIFIC_SCHEMA = N'software_eng' 
)
DROP PROCEDURE CreateCustomer
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE CreateCustomer
  @EMAIL    VARCHAR(128) = '', 
  @HASH     CHAR(128)    = '', 
  @FULLNAME VARCHAR(64)  = ''

-- add more stored procedure parameters here
AS
  -- body of the stored procedure
  INSERT INTO users (passwdHash, fullName, email, accountType)
  VALUES (@HASH, @FULLNAME, @EMAIL, 2); 
GO
-- example to execute the stored procedure we just created
EXECUTE CreateCustomer 'nobody@nowhere.com' , 'cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e' /*Hash of empty string ('')*/, 'Jane Doe'
GO