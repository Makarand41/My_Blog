
# Blog Application

This is a simple blog application developed using Spring Boot and JPA. Users can sign up, log in, create posts, and manage their posts.

## Technologies Used
- Java
- Spring Boot
- Hibernate
- MySQL

## Setup

### Prerequisites

- Java Development Kit (JDK) - [Download](https://www.oracle.com/java/technologies/javase-downloads.html)
- MySQL Database - [Download](https://www.mysql.com/downloads/)

### Clone the Repository

1. Clone the Repository
    git clone https://github.com/your-username/blog-application.git

2. Navigate to the project directory
cd blog-application

3.  Create a MySQL database for the application and update the configuration

4. Build and run the application
./mvnw spring-boot:run

5. The application will be accessible at http://localhost:8023.

## Endpoints


1. Create a User:

Endpoint: POST http://localhost:8023/user/signup
Description: Create a new user.

2. Login:

Endpoint: POST http://localhost:8023/user/login
Description: Log in with an existing user.

3. Delete a User:

Endpoint: DELETE http://localhost:8023/user/delete
Description: Delete a user by providing email and password in the request body.

4. Create a Post:

Endpoint: POST http://localhost:8023/user/{userEmail}/posts
Description: Create a new post for the specified user.

5. Get All Posts for a User:

Endpoint: GET http://localhost:8023/user/{userEmail}/posts
Description: Get all posts for the specified user.

6. Get a Specific Post for a User:

Endpoint: GET http://localhost:8023/user/{userEmail}/posts/{postId}
Description: Get a specific post for the specified user.

7. Delete a Post:

Endpoint: DELETE http://localhost:8023/user/{userEmail}/posts/{postId}
Description: Delete a post for the specified user.

8. Delete All Posts for a User:

Endpoint: DELETE http://localhost:8023/user/{userEmail}/posts
Description: Delete all posts for the specified user.
