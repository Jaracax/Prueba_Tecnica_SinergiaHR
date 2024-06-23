# Registro Login REST API

## Introduction
The Registro Login API provides endpoints for user registration, login, and password reset functionality. 
It uses Spring Boot and JPA with MySQL for persistence.

#### Summary of Functionalities
- **Registration Endpoint:** Allows new users to register by providing their first name, last name, email, and password, with necessary validations.
- **Login Endpoint:** Authenticates users by verifying their email and password, ensuring both fields meet the validation criteria.
- **Password Reset Endpoint:** Provides functionality to reset the user's password.

## Technologies Used
- Java
- Maven
- Spring Boot
- Spring Data JPA
- MySQL

### Setting up the project locally
1. Install java 17
1. install MySql 8.0
1. Clone the repository `git clone https://github.com/Jaracax/Prueba_Tecnica_SinergiaHR.git`
1. Run a MySql database instance
1. Update `application.properties` with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```
1. Create the following table
   ```sql
   Create table user (id int primary key auto_increment, name varchar (50) not null, last_name varchar(50) not null, email varchar(75) not null, password varchar (100) not null);
    ```
1. Run the application
1. Import the postman collection to send http requests to the API endpoints. [Register_Login.postman_collection](src/main/resources/postman/Register_Login.postman_collection.json)

## API Endpoints

### User Registration

**URI:** `POST /api/registration`

**Description**
   - **Endpoint:** Provides fields for "First name", "Last name", "Email", and "Password".
   - **Email and Password Validation:** The email field must include the '@' character and the password
     must be at least 8 characters long, include at least one uppercase letter, one lowercase letter, one number, and one special character.
   - **Error Handling:** Appropriate error messages are returned for invalid inputs.

**Request Body:**
```json
{
    "name": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "Password123!"
}
```

**Response Body:**
```json
{
    "name": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
}
```

### User Login

**URI:** `POST /api/login`

**Description**
   - **Endpoint:** Allows users to log in using their email and password.
   - **Error Handling:** If the email or password is incorrect, the API responds with "Incorrect username/password" without specifying which field is wrong.

**Request Body:**
```json
{
    "email": "john.doe@example.com",
    "password": "Password123!"
}
```

**Response Statuses:**
- `200 OK` if login is successful
- `404 Not Found` if email or password is incorrect with the message "email/contraseña incorrecto"

### Reset Password

**URI:** `POST /api/resetPassword`

   **Description**
   - **Endpoint:** Resets the user's password as it replaces it with a new one wrote by the user.
   - **Validation:** Ensures that the new password adheres to the specified password policy.

**Request Body:**
```json
{
    "email": "john.doe@example.com",
    "password": "NewPassword123!"
}
```

**Response:**
- `200 OK` if password reset is successful
- `400 Bad Request` if the request is invalid
  
## Project Structure

```
src/main/java/com/example/registro_login
├── persistence
│   ├── entity
│   │   └── User.java
│   └── repository
│       └── UserRepository.java
├── rest
│   ├── request
│   │   ├── LoginRequest.java
│   │   ├── RegisterRequest.java
│   │   └── ResetPasswordRequest.java
│   ├── response
│   │   ├── LoginResponse.java
│   │   ├── RegisterResponse.java
│   │   └── ResetPasswordResponse.java
│   └── UserController.java
├── service
│   ├── dto
│   │   ├── UserLoginDto.java
│   │   ├── UserPasswordResetDto.java
│   │   └── UserRegistrationDto.java
│   ├── mapper
│   │   ├── UserLoginMapper.java
│   │   ├── UserPasswordResetMapper.java
│   │   └── UserRegistrationMapper.java
│   └── UserService.java
└── RegistroLoginApplication.java
```

## Missing Implementation 

The following functionalities were intended for the API but were not implemented:

- **Thymeleaf Interface:** A user-friendly web interface using Thymeleaf templates to interact with the API.
- **Email Sender Feature:** Integration of email sending capabilities to automate password reset emails.
