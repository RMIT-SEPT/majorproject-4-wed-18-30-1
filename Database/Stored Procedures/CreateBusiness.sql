-- Create a new stored procedure called 'CreateBusiness' in schema 'software_eng'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
  FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_NAME = N'CreateBusiness'
--  AND SPECIFIC_SCHEMA = N'software_eng'
)
DROP PROCEDURE CreateBusiness
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE CreateBusiness
  @EMAIL    VARCHAR(128) = '', 
  @HASH     CHAR(128)    = '', 
  @FULLNAME VARCHAR(64)  = '',
  @BUSNAME  VARCHAR(32)  = '', 
  @ADDRESS  VARCHAR(32)  = '', 
  @POSTCODE INT          = 0

-- add more stored procedure parameters here
AS
  -- body of the stored procedure
  INSERT INTO users (passwdHash, fullName, email, accountType)
  VALUES (@HASH, @FULLNAME, @EMAIL, 0);

  --Get assigned private ID for linking to managed business
  DECLARE @PRIVATEID int;
  SET @PRIVATEID = (SELECT TOP 1 privateID FROM users
                    WHERE passwdHash = @HASH
                    AND email = @EMAIL)
  
  INSERT INTO businesses (businessName, streetAddress, postcode, ownerID)
  VALUES (@BUSNAME, @ADDRESS, @POSTCODE, @PRIVATEID);

  --Return the user ID assigned to the new business owner
  SELECT TOP 1 publicID FROM users
  WHERE passwdHash = @HASH
  AND email = @EMAIL
GO
-- example to execute the stored procedure we just created
EXECUTE CreateBusiness 'nobody@nowhere.com' , 'cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e' /*Hash of empty string ('')*/, 'Jane Doe', 'ACME inc.', '123 main street', 3000
GO