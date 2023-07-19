# API Documentation

## Get Posts
Retrieves a list of posts.

- **URL:** `/api/posts`
- **Method:** `GET`
- **Parameters:**
    - `pageNo` (optional): The page number for pagination. Default value is 0.
    - `pageSize` (optional): The page size for pagination. Default value is 10.
- **Response:**
    - HTTP Status Code: `200 OK`
    - Body: List of `PostDto` objects.

## Create Post
Creates a new post.

- **URL:** `/api/admin/post/create-post`
- **Method:** `POST`
- **Request Body:** `PostDto` object (validated)
- **Response:**
    - HTTP Status Code: `200 OK`
    - Body: `PostDto` object of the created post.

## Delete Post
Deletes a post with the specified ID.

- **URL:** `/api/admin/post/delete/{id}`
- **Method:** `DELETE`
- **Path Parameter:** `id` (required): ID of the post to delete.
- **Response:**
    - HTTP Status Code: `200 OK`
    - Body: A `HashMap` with a success message.

## Update Post
Updates an existing post.

- **URL:** `/api/admin/post/update`
- **Method:** `PUT`
- **Request Body:** `PostDto` object (validated)
- **Response:**
    - HTTP Status Code: `200 OK`
    - Body: `PostDto` object of the updated post.


  
## Get Comments
Retrieves all comments for a specific post.

- **URL:** `/api/comments`
- **Method:** `GET`
- **Parameters:**
  - `postId` (required): ID of the post to retrieve comments for.
- **Response:**
  - HTTP Status Code: `200 OK`
  - Body: List of `CommentDto` objects.

## Create Comment
Creates a new comment.

- **URL:** `/api/comment/new-comment`
- **Method:** `POST`
- **Request Body:** `CommentDto` object
- **Response:**
  - HTTP Status Code: `200 OK`
  - Body: `CommentDto` object of the created comment.

## Delete Comment
Deletes a comment with the specified ID.

- **URL:** `/api/comment/delete/{id}`
- **Method:** `DELETE`
- **Path Parameter:** `id` (required): ID of the comment to delete.
- **Response:**
  - HTTP Status Code: `200 OK`
  - Body: A `HashMap` with a success message.

## Update Comment
Updates an existing comment.

- **URL:** `/api/comment/update`
- **Method:** `PUT`
- **Request Body:** `CommentDto` object
- **Response:**
  - HTTP Status Code: `200 OK`
  - Body: `CommentDto` object of the updated comment.


## Create User
Creates a new user.

- **URL:** `/api/auth/signup`
- **Method:** `POST`
- **Request Body:** `SignupDto` object
- **Response:**
  - HTTP Status Code: `200 OK` (on success), `409 Conflict` (if email is already registered)
  - Body: `UserDto` object of the created user or an `ErrorResponse` object in case of conflict.

## User Login
Performs user login and provides a JWT token.

- **URL:** `/api/auth/login`
- **Method:** `POST`
- **Request Body:** `LoginDto` object
- **Response:**
  - HTTP Status Code: `200 OK` (on success)
  - Body: `JwtResponse` object containing the JWT token, email, and roles of the authenticated user.

Please note that for the `/api/auth/login` endpoint, the user's email and password are required in the request body. Upon successful authentication, a JWT token is generated and returned in the response.

## Security Configuration

- **Class:** `WebSecurityConfig`
- **Package:** `com.app.blog_api.config`

## Security Rules

- **Public Endpoints (No Authentication Required):**
  - `/api/posts` (All methods)
  - `/api/auth/**` (All methods)
  - `/api/comments` (All methods)
  - `/api/comment/**` (All methods)

- **Admin Endpoints (Requires ADMIN Role):**
  - `/api/admin/**` (All methods)

- **Authenticated Endpoints (Requires Authentication):**
  - All other endpoints not listed above

Please note that for the public endpoints, no authentication is required, allowing access to unauthenticated users. The admin endpoints require the user to have the "ADMIN" role. All other endpoints require authentication, meaning the user must be logged in to access them.

Additionally, the `WebSecurityConfig` class sets up CORS (Cross-Origin Resource Sharing) to allow requests from any origin, header, and method.