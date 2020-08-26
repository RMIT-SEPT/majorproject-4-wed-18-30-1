-- Create a new stored procedure called 'AddAvailability' in schema  
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
  FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_NAME = N'AddAvailability'
)
DROP PROCEDURE AddAvailability
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE AddAvailability
  @email VARCHAR(128) = 'INVALID-EMAIL',
  @startTime int      = 0,
  @endTime   int      = 60
-- add more stored procedure parameters here
AS
  -- body of the stored procedure
  IF EXISTS (SELECT TOP(1) 1 FROM accessibleTimes
      WHERE accessibleTimes.targetID = @email
      AND dayNum = @startTime / 86400)
  BEGIN
    UPDATE accessibleTimes
    SET targetID = @email, startTime = @startTime, endTime = @endTime
    WHERE  accessibleTimes.targetID = @email
    AND accessibleTimes.dayNum = @startTime / 86400;
  END

  ELSE
  BEGIN
    IF EXISTS (SELECT TOP(1) 1 FROM users
        WHERE email = @email)
    BEGIN
      INSERT INTO accessibleTimes (targetID, frequency, startTime, endTime)
      VALUES (@email, @startTime, @endTime)
    END
    
    ELSE
    BEGIN
      PRINT 'USER' + @email + 'DOES NOT EXIST';
    END
  END
GO
-- example to execute the stored procedure we just created
EXECUTE AddAvailability 'joe.danger@Hello.com', 0, 60
GO