SELECT * FROM users, businesses, employees
--User exists on table "employees"
WHERE users.accountType = 1 
--User record matches employee record
AND employees.privateID = users.privateID
--Business record matches employee record 
AND employees.businessID = businesses.businessID