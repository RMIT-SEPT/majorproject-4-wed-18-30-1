-- Create a new stored procedure called 'EncryptID' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
  FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
  AND SPECIFIC_NAME = N'EncryptID'
)
DROP PROCEDURE dbo.EncryptID
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.EncryptID
  @PrivateID /*plaintext ID of a user, buissness, meeting, ect.*/ int = 0, /*default_value_for_param1*/
  @N /*Value of a number equal to p*q where p and q are both primes*/ int = 0,
  @e /*any number that fufills 1<e<lcm(p-1,q-1) and is coprime to lcm(p-1,q-1)*/
AS
  -- body of the stored procedure
  POWER(@PrivateID + 1, @e) % @N
RETURN
END
GO
-- example to execute the stored procedure we just created
EXECUTE dbo.EncryptID 1
GO 