# Note Microservice

The Note microservice runs on port 8081 and facilitates the management of user-specific notes. The provided API endpoints include:

1. **GET /api/notes:**
   - Retrieve a list of all notes for the authenticated user.

2. **GET /api/notes/{id}:**
   - Retrieve a specific note by ID for the authenticated user.

3. **POST /api/notes:**
   - Create a new note for the authenticated user.

4. **PUT /api/notes/{id}:**
   - Update an existing note by ID for the authenticated user.

5. **DELETE /api/notes/{id}:**
   - Delete a note by ID for the authenticated user.

6. **POST /api/notes/{id}/share:**
   - Share a note with another user for the authenticated user.

7. **GET /api/notes/search?q={query}:**
   - Search for notes based on keywords for the authenticated user.

**Note: Users have access only to their own notes.**

These APIs are designed to be accessible only to authenticated users who possess a valid access token and have included their username in the headers. Ensure that the necessary authentication details are provided in the headers to interact with these endpoints securely.
