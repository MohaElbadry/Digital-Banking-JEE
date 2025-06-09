<p align="center">
  <img src="https://img.icons8.com/?size=100&id=NdeIzskkIm2U&format=png&color=000000" alt="Digital Banking" width="120" height="120">
</p>

<h1 align="center">Digital Banking Platform</h1>

<p align="center">
  <strong>Modern Banking Solutions</strong><br>
  A comprehensive financial management system built with Spring Boot
</p>

<div align="center">
  
  ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen?style=flat-square&logo=spring)
  ![Java](https://img.shields.io/badge/Java-21+-orange?style=flat-square&logo=java)
  ![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)
  
</div>

---

## Core Features

- Secure Authentication System with OAuth2
- Customer Management
- Account Operations
- Transaction Processing
- Role-Based Access Control
- RESTful API Documentation with Swagger/OpenAPI

## Technology Stack

### Backend Stack
```yaml
Runtime: Java 21
Framework: Spring Boot 3.4.5
Security: Spring Security + OAuth2 Resource Server
Database: MySQL / H2
ORM: Spring Data JPA
Documentation: SpringDoc OpenAPI 2.1.0
Build: Maven
```

### Key Dependencies
```yaml
Spring Boot Starter:
  - Web
  - Data JPA
  - DevTools
  - OAuth2 Resource Server
  - Security Test
Database:
  - MySQL Connector
  - H2 Database
Tools:
  - Lombok
  - SpringDoc OpenAPI
```

---

## Quick Start Guide

### Prerequisites
- Java 21
- Maven 3.6+
- MySQL (optional, H2 is included for development)

### Setup
```bash
# Clone & Navigate
git clone https://github.com/yourusername/digital-banking-jee.git
cd digital-banking-jee

# Run the application
mvn spring-boot:run
```

### Access Points
| Service | URL | Purpose |
|---------|-----|---------|
| Application | http://localhost:8080 | Main Application |
| H2 Console | http://localhost:8080/h2-console | Database Admin |
| API Docs | http://localhost:8080/swagger-ui.html | Interactive API |

---

## Project Structure
```
src/main/java/ma/enset/digitalbanking/
├── config/          # Configuration classes
├── dtos/           # Data Transfer Objects
├── entities/       # Domain entities
├── enums/          # Enumeration types
├── exceptions/     # Custom exceptions
├── mappers/        # Object mappers
├── repositories/   # Data access layer
├── security/       # Security configuration
├── services/       # Business logic
└── web/           # REST controllers
```

## API Documentation

### Core Endpoints

#### Authentication APIs
```http
POST   /api/auth/login     # User authentication
POST   /api/auth/logout    # Session termination
GET    /api/auth/profile   # User profile data
```

#### Customer Management APIs
```http
GET    /api/customers              # List all customers
POST   /api/customers              # Create new customer
GET    /api/customers/{id}         # Get customer details
PUT    /api/customers/{id}         # Update customer
DELETE /api/customers/{id}         # Remove customer
```

#### Account Management APIs
```http
GET    /api/accounts               # List all accounts
POST   /api/accounts               # Create new account
GET    /api/accounts/{id}          # Account details
PUT    /api/accounts/{id}          # Update account
DELETE /api/accounts/{id}          # Close account
```

#### Transaction APIs
```http
POST   /api/operations/debit       # Debit transaction
POST   /api/operations/credit      # Credit transaction
POST   /api/operations/transfer    # Transfer funds
GET    /api/operations/{id}        # Transaction details
GET    /api/operations/history     # Transaction history
```

---

## Configuration

### Environment Variables
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
<dependency>****
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>********
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

### Database Configuration
```yaml
# application.yml
spring:
  datasource:
    url: jdbc:h2:mem:banking_db
    driver-class-name: org.h2.Driver
    username: sa
    password: 
  jpa:<****
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```
### UML
```mermaid
classDiagram
    class Customer {
        +Long id
        +String name
        +String email
        +List~BankAccount~ bankAccounts
    }
    
    class BankAccount {
        +String id
        +double balance
        +Date createDate
        +AccountStatus status
        +Customer customer
        +List~AccountOperation~ accountOperations
    }
    
    class CurrentAccount {
        +double overDraft
    }
    
    class SavingAccount {
        +double interestRate
    }
    
    class AccountOperation {
        +Long id
        +Date operationDate
        +double amount
        +OperationType type
        +BankAccount bankAccount
        +String description
    }
    
    class OperationType {
        <<enumeration>>
        CREDIT
        DEBIT
    }
    
    class AccountStatus {
        <<enumeration>>
        CREATED
        ACTIVATED
        SUSPENDED
    }
    
    Customer ||--o{ BankAccount
    BankAccount ||--o{ AccountOperation
    BankAccount <|-- CurrentAccount
    BankAccount <|-- SavingAccount
    AccountOperation --> OperationType
    BankAccount --> AccountStatus
```
### Flux 

```mermaid
sequenceDiagram
    participant U as Utilisateur
    participant F as Frontend
    participant B as Backend
    participant DB as Database

    U->>F: Saisie login/password
    F->>F: Validation formulaire
    F->>B: POST /auth/login
    B->>DB: Vérification credentials
    DB-->>B: Utilisateur trouvé
    B->>B: Génération JWT token
    B-->>F: Token + rôles
    F->>F: Stockage token
    F->>F: Redirection dashboard
    F->>U: Interface selon rôle
```

---

## Future Roadmap

### Phase 2 - Enhanced Features
- [ ] Responsive design for Mobile
- [ ] Analytics dashboard
- [ ] Multi-currency support
- [ ] AI-powered Chat-Bot

### Phase 3 - Enterprise Features
- [ ] Microservices migration
- [ ] Kubernetes deployment
- [ ] Third-party integrations

---

<div align="center">

### Star This Project

*If you find this banking platform valuable, please give it a star!*

---

**Digital Banking Platform - Modern Banking Solutions**

</div>