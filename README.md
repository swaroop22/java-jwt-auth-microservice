# JWT Authentication Microservice

A production-ready Java microservice implementing JWT-based authentication with role-based access control (RBAC). This service provides secure authentication for ADMIN and USER roles with different access levels.

## Features

- ✅ JWT token generation and validation
- ✅ Role-based access control (ADMIN, USER)
- ✅ Spring Security integration
- ✅ H2 in-memory database
- ✅ RESTful API endpoints
- ✅ Password encryption with BCrypt
- ✅ Token expiration handling
- ✅ Comprehensive security configuration

## Technologies

- **Java 17**
- **Spring Boot 3.2.1**
- **Spring Security**
- **JWT (JJWT 0.12.3)**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Maven**

## Project Structure

```
src/main/java/com/auth/
├── JwtAuthApplication.java
├── config/
│   ├── SecurityConfig.java
│   └── JwtAuthenticationFilter.java
├── controller/
│   ├── AuthController.java
│   ├── AdminController.java
│   └── UserController.java
├── model/
│   ├── User.java
│   ├── Role.java
│   └── AuthRequest.java
├── service/
│   ├── JwtService.java
│   ├── UserService.java
│   └── CustomUserDetailsService.java
└── repository/
    └── UserRepository.java
```

## Quick Start

### Prerequisites

- JDK 17 or higher
- Maven 3.6+

### Build & Run

```bash
# Clone the repository
git clone https://github.com/swaroop22/java-jwt-auth-microservice.git
cd java-jwt-auth-microservice

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Authentication

**Register User**
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "user@example.com",
  "password": "password123",
  "role": "USER"
}
```

**Login**
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "user@example.com",
  "password": "password123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "user@example.com",
  "role": "USER"
}
```

### User Endpoints (Requires USER or ADMIN role)

```http
GET /api/user/profile
Authorization: Bearer {token}
```

```http
GET /api/user/dashboard
Authorization: Bearer {token}
```

### Admin Endpoints (Requires ADMIN role only)

```http
GET /api/admin/users
Authorization: Bearer {token}
```

```http
DELETE /api/admin/users/{id}
Authorization: Bearer {token}
```

```http
GET /api/admin/dashboard
Authorization: Bearer {token}
```

## Implementation Details

### Role-Based Access Control

- **ADMIN Role**: Full access to all endpoints including user management
- **USER Role**: Limited access to user-specific endpoints only
- Unauthorized users receive 401 or 403 status codes

### Security Features

1. **Password Encryption**: All passwords are encrypted using BCrypt
2. **JWT Tokens**: Stateless authentication using JWT
3. **Token Expiration**: Tokens expire after 24 hours
4. **CORS**: Configured for secure cross-origin requests
5. **CSRF Protection**: Disabled for stateless JWT authentication

### JWT Token Structure

The JWT token contains:
- Subject (username)
- Roles/Authorities
- Issue date
- Expiration date
- Custom claims

## Configuration

Key configurations in `application.properties`:

```properties
# JWT Secret (should be environment variable in production)
jwt.secret=YOUR_SECRET_KEY_HERE
jwt.expiration=86400000

# Server Port
server.port=8080

# Database
spring.datasource.url=jdbc:h2:mem:jwtdb
spring.h2.console.enabled=true
```

## Testing with cURL

**Register an admin user:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"admin@test.com","password":"admin123","role":"ADMIN"}'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin@test.com","password":"admin123"}'
```

**Access protected endpoint:**
```bash
curl -X GET http://localhost:8080/api/admin/dashboard \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

## Database Console

Access H2 console at: `http://localhost:8080/h2-console`

- **JDBC URL**: `jdbc:h2:mem:jwtdb`
- **Username**: `sa`
- **Password**: (leave blank)

## Security Best Practices

⚠️ **Important for Production:**

1. **JWT Secret**: Store in environment variables, not in code
2. **HTTPS**: Always use HTTPS in production
3. **Token Storage**: Store tokens securely (HttpOnly cookies recommended)
4. **Password Policy**: Implement strong password requirements
5. **Rate Limiting**: Add rate limiting to prevent brute force attacks
6. **Token Refresh**: Implement refresh token mechanism
7. **Logging**: Add security event logging
8. **Database**: Use PostgreSQL/MySQL instead of H2

## Development Setup

### Run Tests
```bash
mvn test
```

### Package Application
```bash
mvn package
java -jar target/jwt-auth-microservice-1.0.0.jar
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

**Swaroop** - [GitHub](https://github.com/swaroop22)

## Acknowledgments

- Spring Security documentation
- JWT.io for JWT debugging
- Spring Boot community

---

**Note**: This is a demonstration project. For production use, ensure proper security audits and implement additional security measures as mentioned in the Security Best Practices section.
