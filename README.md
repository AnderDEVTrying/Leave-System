
# Leave System API

This project is an API for managing leave requests in an access and permissions control system. The application is built using **Spring Boot** and **Spring Security**. Users can register, log in, and submit leave requests, while administrators can manage the status of those requests.

## **Features**

- User registration with different roles (e.g., ADMIN and EMPLOYEE).
- User authentication using JWT tokens.
- Creation and viewing of leave requests.
- Management of request status (approved, rejected, etc.).

## **Technologies Used**

- Java
- Spring Boot
- Spring Security
- JWT (Json Web Token) for authentication
- BCrypt for password encryption
- PostgreSQL Database (or other databases of choice)

## **Configuration**

### **Prerequisites**

- Java 17+
- Maven
- PostgreSQL (or any supported database)

### **Installation**

1. Clone the repository:
   ```bash
   git clone https://github.com/AnderDEVTrying/Leave-System.git
   ```
2. Navigate to the project directory:
   ```bash
   cd LeaveSystem
   ```
3. Install dependencies and compile the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Update the configuration in `application.properties`:

```properties
spring.application.name=leaveSystem
spring.datasource.url=jdbc:postgresql://localhost:5433/leaveSystemTest
spring.datasource.username=postgres
spring.datasource.password=pass

api.security.secret = ${JWT_SECRET:forever-secret}
```

## **Endpoints**

### **Authentication**

#### `POST /auth/login`
Authenticates the user and returns a JWT token.

- **Request Body**:
  ```json
  {
    "email": "user@example.com",
    "password": "password123"
  }
  ```

- **Response**:
  - Success: `200 OK` with JWT token.
  - Failure: `400 Bad Request`.

#### `POST /auth/register`
Registers a new user.

- **Request Body**:
  ```json
  {
    "userName": "User Name",
    "email": "user@example.com",
    "password": "password123",
    "role": "REGULAR"
  }
  ```

- **Response**:
  - Success: `200 OK` with a success message.
  - Failure: `400 Bad Request` if the email is already registered.

### **Forms**

#### `POST /forms/create`
Creates a new leave request.

- **Request Body**:
  ```json
{
    "type": "vacation",
  "days": 5,
  "dateFrom": "2024-09-25",
  "motive": "Taking a break for family vacation"
  }
  ```

- **Response**:
  - Success: `200 OK` with confirmation message.
  - Failure: `400 Bad Request` in case of error.

#### `GET /forms/requests`
Fetches all leave requests for the authenticated user, or if an **ADMIN**, fetches all requests in the system.

- **Response**:
  - List of leave requests.

#### `PUT /forms/status/{id}`
Updates the status of a leave request (only accessible by **ADMIN**).

- **Request Body**:
  ```json
  {
    "APPROVED"
  }
  ```

- **Response**:
  - Success: `200 OK` with a success message.
  - Failure: `403 Forbidden` if the user is not an admin.

## **Data Models**

### **LoginRequestDTO**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

### **RegisterRequestDTO**
```json
{
  "userName": "User Name",
  "email": "user@example.com",
  "password": "password123",
  "role": "EMPLOYEE"
}
```

### **FormRequestDTO**
```json
{
  "startDate": "2024-09-23",
  "endDate": "2024-09-30",
  "reason": "Reason for leave"
}
```

## **Security**

The application uses **Spring Security** to secure endpoints and authenticate users. Passwords are encrypted using **BCrypt**, and authentication is handled with **JWT (JSON Web Token)**.

### **Roles and Permissions**
- **ADMIN**: Can view and update the status of all leave requests.
- **EMPLOYEE**: Can create and view their own leave requests.

## **Environment Variables**

Make sure to configure the following environment variables:

- `JWT_SECRET`: The secret key for JWT token generation. If not provided, the default will be used (`forever-secret`).

Example `.env` file:

```env
JWT_SECRET=your-secret-key
```

## **Contributing**

1. Fork the project.
2. Create a new branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m 'Added a new feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Open a Pull Request.

## **License**

This project is licensed under the MIT License.


