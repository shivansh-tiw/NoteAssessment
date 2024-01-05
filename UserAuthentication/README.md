# UserAuthentication Microservice

The UserAuthentication microservice runs on port 8080, providing robust user authentication functionalities. The following API endpoints are available:

1. **POST /api/auth/signup:**
   - Create a new user account by providing a username and password in the request body.

2. **POST /api/auth/login:**
   - Log in to an existing user account by providing the username and password in the request body. Receive an access token upon successful authentication.

**Access Token Usage:**
   - Include the access token in the "authorization" header for subsequent requests to the Note microservice.
   - Include the username in the "username" header for additional security measures.

These authentication mechanisms ensure secure access to the Note microservice APIs. Note that only authenticated users with valid access tokens and matching usernames in the headers are granted access.
