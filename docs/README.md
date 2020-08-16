# SEPT Startup code and  project Structure documentation 

# Quick Start

## USER API

### Set up

Ensure that the Postman Collection located at `majorproject-4-wed-18-30-1/BackEnd/src/main/resources/postman/USER.postman_collection.json`
has been imported before continuing.

### Create User

Run the `POST` query. A JSON object has been pre configured to create a user, so you just need hit SEND. You should receive a 200 status code.
  
  To confirm you can visit the H2 console located at `http://localhost:8080/h2-console`

### Delete a user

Run the `DEL` query. A JSON object has been pre configured to create a user, so you just need hit SEND. You should receive a 200 status code.


### View users

Run the `GET` query. A JSON object has been pre configured to create a user, so you just need hit SEND. You should receive a 200 status code.

### Edit users

TODO: This requires implementation.

## Authentication

The authentication currently makes use of Spring Security and Spring data JPA.
The password is currently stored in the `USER` table as a raw string and will need to be hashed before deployment.

Spring Securities default login page is used to handle the access request.

TODO: Add a redirect based on the ROLES attribute of a user after a successful login.