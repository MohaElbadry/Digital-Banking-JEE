# Digital Banking Application

## Overview

This digital banking application is built using Java EE technologies. It provides a robust platform for users to manage their bank accounts, perform various transactions, and access banking services. The project implements a RESTful API architecture to handle banking operations securely and efficiently.

## Features

- Customer Management (CRUD operations)
- Account Management (Current and Saving accounts)
- Transaction Operations (Debit, Credit, Transfer)
- Account History and Statement Generation
- Pagination for Transaction History

## Technology Stack

- **Backend**:
  - Java 21
  - Spring Boot 3.4.5
  - Spring Data JPA
  - Hibernate ORM
  - MySQL Database
  - RESTful API
- **Documentation**:
  - SpringDoc OpenAPI (Swagger UI)
- **Testing**:
  - JUnit
  - Spring Test Framework
- **Development Tools**:
  - Maven
  - Lombok

## Project Structure

```
Digital-Banking-JEE/
├── src/
│   ├── main/
│   │   ├── java/ma/enset/digitalbanking/
│   │   │   ├── dtos/                  # Data Transfer Objects
│   │   │   ├── entities/              # JPA Entity classes
│   │   │   ├── enums/                 # Enumeration types
│   │   │   ├── exceptions/            # Custom exception classes
│   │   │   ├── mappers/               # Object mappers (Entity to DTO)
│   │   │   ├── repositories/          # Spring Data repositories
│   │   │   ├── services/              # Business logic layer
│   │   │   ├── web/                   # REST controllers
│   │   │   └── DigitalBankingApplication.java   # Main application class
│   │   └── resources/
│   │       └── application.properties  # Application configuration
│   └── test/                          # Test classes
├── .mvn/                              # Maven wrapper directory
├── mvnw                               # Maven wrapper script (Unix)
├── mvnw.cmd                           # Maven wrapper script (Windows)
├── pom.xml                            # Project Object Model
└── README.md                          # Project documentation
```

## Layer Descriptions

### DTOs (Data Transfer Objects)

Contains classes that transfer data between processes, reducing the amount of data that needs to be sent across the wire and decoupling the service layer from the persistence layer.

### Entities

JPA entity classes that map to database tables. The main entities include:

- `Customer`: Stores customer information
- `BankAccount`: Abstract base class for bank accounts
- `CurrentAccount`: Checking account with overdraft facility
- `SavingAccount`: Savings account with interest rate
- `AccountOperation`: Represents transactions on accounts

### Enums

Enumeration types used throughout the application:

- `AccountStatus`: Status of a bank account (CREATED, ACTIVATED, SUSPENDED, BLOCKED)
- `OperationType`: Type of account operation (DEBIT, CREDIT)

### Exceptions

Custom exception classes for handling specific error scenarios:

- `CustomerNotFoundException`: Thrown when a customer is not found
- `BankAccountNotFoundException`: Thrown when a bank account is not found
- `BalanceNotSufficientException`: Thrown when attempting a debit with insufficient balance

### Mappers

Handles transformation between entities and DTOs to decouple the database representation from the API representation.

### Repositories

Spring Data JPA repositories that provide database access operations:

- `CustomerRepository`: CRUD operations for customers
- `BankAccountRepository`: CRUD operations for bank accounts
- `AccountOperationRepository`: CRUD operations for account operations

### Services

Contains the business logic of the application:

- `BankAccountService`: Interface defining banking operations
- `BankAccountServiceImpl`: Implementation of banking operations

### Web

REST controllers that expose the API endpoints:

- `CustomerRestController`: Endpoints for customer management
- `BankAccountRestController`: Endpoints for account management and operations

## API Endpoints

### Customer Endpoints

- `GET /customers`: List all customers
- `GET /customers/{id}`: Get customer by ID
- `POST /customers`: Create a new customer
- `PUT /customers/{id}`: Update a customer
- `DELETE /customers/{id}`: Delete a customer

### Account Endpoints

- `GET /accounts`: List all accounts
- `GET /accounts/{id}`: Get account by ID
- `GET /accounts/{id}/operations`: Get all operations for an account
- `GET /accounts/{id}/pageOperations`: Get paginated operations for an account

## Setup and Installation

1. Clone the repository
2. Configure MySQL database in `application.properties`
3. Run `mvn clean install` to build the project
4. Run `mvn spring-boot:run` to start the application
5. Access the API at `http://localhost:8085`
6. Access Swagger UI at `http://localhost:8085/swagger-ui.html`

## Database Configuration

The application is configured to use MySQL database. You can modify the database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/digital-banking?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
```
