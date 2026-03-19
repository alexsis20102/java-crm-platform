# 🚀 Enterprise CRM Platform (Java Microservices Architecture)

An enterprise-grade CRM system built using a microservices architecture.

The project demonstrates modern backend engineering practices used in production systems, including service discovery, centralized configuration, event-driven architecture, containerization, and CI/CD.

---

# 🎯 Project Goal

The main goal of this project is to demonstrate a proper approach to building microservices architecture:

* clear separation of responsibilities between services
* independent deployment
* asynchronous communication via events
* centralized configuration management
* scalability and fault tolerance

---

# 🛠 Tech Stack

## Backend

* Java 23
* Spring Boot
* Spring Security
* Spring Data JPA

## Microservices Infrastructure

* Spring Cloud Gateway
* Netflix Eureka
* Spring Cloud Config

## Messaging

* Apache Kafka

## Databases

* MySQL

## Caching

* Redis

## DevOps

* Docker
* Docker Compose
* GitHub Actions (CI/CD)

## API Documentation

* Swagger / OpenAPI

---

# 🏗 System Architecture

The system consists of multiple independent microservices.

Each service:

* has its own database
* can be deployed independently
* communicates via REST or Kafka

---

# 📊 Architecture Diagram

```id="9a1x4p"
                                CLIENT
                                   |
                           +---------------+
                           |  API Gateway  |
                           +-------+-------+
                                   |
                      +------------+------------+
                      |                         |
               +------v------+           +------v------+
               | Auth Service|           |User Service |
               +------+------+\           +------+------+
                      |                         |
                      v                         v
                   Redis                      MySQL

                    SERVICE DISCOVERY
                 +---------------------+
                 |    Eureka Server    |
                 +---------------------+

                           |
         +-----------------+------------------+
         |                                    |
   +-----v------+                      +------v------+
   | Customer   |                      |   Order     |
   |  Service   |                      |   Service   |
   +-----+------+                      +------+------+
         |                                    |
         v                                    v
        MySQL                               MySQL

                     EVENT STREAM

                  +------------------+
                  |   Apache Kafka   |
                  +--------+---------+
                           |
            +--------------+--------------+
            |                             |
      +-----v------+               +------v------+
      | Billing    |               | Notification|
      | Service    |               |   Service   |
      +-----+------+               +------+------+
            |                             |
            v                             v
         MySQL                          Email
```

---

# 🧩 Core Components

## API Gateway

Single entry point for all client requests.

Responsibilities:

* request routing
* JWT validation
* rate limiting
* centralized logging

---

## Service Discovery

All services register themselves in the service registry.

This enables:

* dynamic service discovery
* load balancing

---

## Config Server

Centralized configuration management for all services:

* database settings
* Kafka configuration
* JWT secrets
* environment variables

---

## 🧾 Logging Service

A dedicated microservice responsible for collecting and storing logs.

Responsibilities:

* collecting logs from all services
* storing change history
* centralized system auditing

---

## ⚠️ Error Handling Library

A shared error-handling library is used across services.

Responsibilities:

* unified error format
* centralized exception handling
* reuse across all microservices

---

# 🔧 Microservices

## Authentication Service

* user registration
* authentication
* JWT token generation
* password hashing

---

## User Service

* user management
* roles and permissions

---

## Customer Service

* customer management
* contact information
* interaction history

---

## Order Service

* order creation
* order lifecycle management

---

## Billing Service

* invoice generation
* payment tracking

---

## Notification Service

* email notifications
* Kafka event consumers

---

# 🔄 Event-Driven Architecture

Example flow:

1. Order is created
2. Order Service publishes an event
3. Billing Service generates an invoice
4. Notification Service sends an email

---

# ⚡ Caching Layer

Redis is used for:

* session storage
* customer data caching
* performance optimization

---

# 📁 Repository Structure

```id="r6s2m1"
crm-platform

├ infrastructure
│ ├ api-gateway
│ ├ config-server
│ └ eureka-server
│
├ services
│ ├ auth-service
│ ├ user-service
│ ├ customer-service
│ ├ order-service
│ ├ billing-service
│ ├ notification-service
│ └ logging-service
│
├ common
│ └ error-library
│
├ docker
│ ├ kafka
│ ├ mysql
│ └ redis
│
├ docker-compose.yml
└ README.md
```

---

# ▶️ Build the Project

```bash id="3t0c3x"
mvn clean install
```

---

# 🐳 Run the System

```bash id="u5b2v8"
docker compose up --build
```

All services will start automatically.

---

# 🌐 Useful Links

* http://localhost:8761/ — Eureka Dashboard
* http://localhost:8081/ — phpMyAdmin
* http://localhost:8888/actuator/health — Config Server

---

# 📘 API Documentation

```id="6k8g1p"
http://localhost:{port}/swagger-ui.html
```

---

# 🧪 Testing via PowerShell

## Registration

```powershell id="k9d2w1"
Invoke-RestMethod -Method POST -Uri "http://localhost:8088/auth/register" `
-ContentType "application/json" `
-Body '{"email":"test@test.com","password":"1234"}'
```

## Authentication

```powershell id="n2f7q4"
$token = Invoke-RestMethod -Method POST -Uri "http://localhost:8088/auth/login" `
-ContentType "application/json" `
-Body '{"email":"test@test.com","password":"1234"}'
```

## Get All Users (ADMIN, MANAGER)

```powershell id="p8x4s2"
Invoke-RestMethod -Method GET -Uri "http://localhost:8088/users/get-all-users" `
-Headers @{Authorization="Bearer $token"}
```

## Create Customer

```powershell id="w1m9z7"
Invoke-RestMethod -Method POST -Uri "http://localhost:8088/customers/new-customer" `
-Headers @{Authorization="Bearer $token"} `
-ContentType "application/json" `
-Body '{"firstName": "Ivan", "lastName": "Ivanov", "email": "ivan2@test.com", "phone": "+49123456789"}'
```

## Get All Customers

```powershell id="b3c7k5"
Invoke-RestMethod -Method GET -Uri "http://localhost:8088/customers/get-all-customers" `
-Headers @{Authorization="Bearer $token"}
```

## Get Customer by ID

```powershell id="z5v1r8"
Invoke-RestMethod -Method GET -Uri "http://localhost:8088/customers/get-customer/1" `
-Headers @{Authorization="Bearer $token"}
```

## Delete Customer (ADMIN)

```powershell id="y7l2d6"
Invoke-RestMethod -Method DELETE -Uri "http://localhost:8088/customers/delete/1" `
-Headers @{Authorization="Bearer $token"}
```

## Update Customer (ADMIN, MANAGER)

```powershell id="q4n8t3"
Invoke-RestMethod -Method PUT -Uri "http://localhost:8088/customers/update-customer/1" `
-Headers @{Authorization="Bearer $token"} `
-ContentType "application/json" `
-Body '{"firstName": "Ivan", "lastName": "Petrov", "email": "newmail@test.com", "phone": "+49111111111"}'
```

---

# 🔄 CI/CD

Implemented using GitHub Actions:

* build services
* run tests
* build Docker images
* publish containers

---

# 🚀 Future Improvements

* Kubernetes deployment
* ELK (centralized logging)
* distributed tracing
* Prometheus + Grafana monitoring

---
