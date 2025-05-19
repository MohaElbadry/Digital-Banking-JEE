# Digital Banking Application

A modern, secure, and feature-rich banking application built with Spring Boot and Angular, providing a comprehensive solution for digital banking operations.

## 🌟 Features

### Customer Management
- Create, update, and delete customer profiles
- Search customers by name or ID
- View detailed customer information

### Account Management
- Support for multiple account types (Current and Saving accounts)
- Create new accounts for existing customers
- View account details and status
- Suspend or activate accounts

### Transaction Processing
- Deposit funds (credit operation)
- Withdraw funds (debit operation)
- Transfer between accounts
- View transaction history with pagination
- Filter transactions by type, date, or amount

### Security
- JWT-based authentication
- Role-based access control (USER and ADMIN roles)
- Secure API endpoints
- Password encryption

## 🏗️ Architecture

The application follows a modern, layered architecture:

### Backend (Spring Boot)
- **Controller Layer**: REST API endpoints for client interaction
- **Service Layer**: Business logic implementation
- **Repository Layer**: Data access using Spring Data JPA
- **Entity Layer**: JPA entities representing database tables
- **DTO Layer**: Data Transfer Objects for API communication
- **Mapper Layer**: Conversion between entities and DTOs
- **Security Layer**: JWT authentication and authorization

### Database
- Supports both MySQL for production and H2 for development/testing
- JPA/Hibernate for ORM

### Frontend (Angular) - Not included in this repository
- Component-based UI architecture
- Services for API communication
- Guards for route protection
- Interceptors for token management

## 📋 Technical Stack

### Backend
- **Java 11+**
- **Spring Boot 2.7.x**
- **Spring Security** with JWT
- **Spring Data JPA**
- **Hibernate**
- **Maven** for dependency management
- **MySQL/H2** databases

### API Documentation
- Swagger UI for API documentation and testing

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- MySQL (optional, can use H2 in-memory database)

### Configuration
The application can be configured via `application.properties`:

```properties
# Application name and port
spring.application.name=digital-banking
server.port=8085

# Database Configuration
# For H2 in-memory database (development/testing)
spring.datasource.url=jdbc:h2:mem:digital-bank
spring.h2.console.enabled=true

# For MySQL (production)
# spring.datasource.url=jdbc:mysql://localhost:3306/digital-banking?createDatabaseIfNotExist=true
# spring.datasource.username=root
# spring.datasource.password=
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# JPA/Hibernate settings
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# JWT Secret
jwt.secret=9faa372517ac1d389764739hfs9397365na5783azc083729faa372517ac1d389
```

### Running the Application

1. Clone the repository:
```bash
git clone https://github.com/yourusername/digital-banking-jee.git
cd digital-banking-jee
```

2. Build the application:
```bash
./mvnw clean install
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

4. Access the application:
   - API: http://localhost:8085
   - H2 Console (if using H2): http://localhost:8085/h2-console
   - Swagger UI: http://localhost:8085/swagger-ui.html

### Authentication

The application comes with pre-configured users:

**Admin User:**
- Username: `admin`
- Password: `12345`
- Roles: ADMIN, USER

**Regular User:**
- Username: `user1`
- Password: `12345`
- Role: USER

To authenticate, send a POST request to `/auth/login` with username and password parameters.

Example:
```bash
curl -X POST "http://localhost:8085/auth/login?username=admin&password=12345"
```

The response will contain a JWT token to use in subsequent requests:
```json
{
  "access_token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6..."
}
```

Use this token in the Authorization header for protected endpoints:
```bash
curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6..." http://localhost:8085/customers
```

## 📦 Dependencies

The project uses the following main dependencies:

```xml
<!-- Spring Boot Starter Web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Spring Boot Starter Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Spring Boot Starter Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- Spring Boot OAuth2 Resource Server -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>

<!-- H2 Database -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- MySQL Connector -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<!-- Spring Boot DevTools -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

## 📝 API Endpoints

### Authentication
- `POST /auth/login`: Authenticate user and get JWT token
- `GET /auth/profile`: Get authenticated user profile

### Customers
- `GET /customers`: Get all customers
- `GET /customers/search?keyword=`: Search customers by name
- `GET /customers/{id}`: Get customer by ID
- `POST /customers`: Create new customer
- `PUT /customers/{id}`: Update customer
- `DELETE /customers/{id}`: Delete customer

### Accounts
- `GET /accounts`: Get all accounts
- `GET /accounts/{id}`: Get account by ID
- `GET /customers/{customerId}/accounts`: Get accounts by customer ID
- `POST /customers/{customerId}/current-accounts`: Create current account
- `POST /customers/{customerId}/saving-accounts`: Create saving account

### Operations
- `GET /accounts/{accountId}/operations`: Get operations for account
- `POST /accounts/debit`: Debit operation
- `POST /accounts/credit`: Credit operation
- `POST /accounts/transfer`: Transfer between accounts

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- JHipster for inspiration on project structure
- All open-source contributors whose libraries made this project possible
