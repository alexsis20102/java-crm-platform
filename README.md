# 🚀 Enterprise CRM Platform (Java Microservices Architecture)

An enterprise-grade CRM system built using a microservices architecture.

The project demonstrates modern backend engineering practices used in production systems, including service discovery, centralized configuration, event-driven architecture, containerization, and CI/CD.

---

# 🆕 Latest Updates

* ✅ Added **Product Service** (product management service)
* 📊 Added monitoring and tracing:

    * Prometheus — http://localhost:9090
    * Zipkin — http://localhost:9411
    * Grafana — http://localhost:3000 (login: admin / password: admin)
* 🐳 Updated system startup via Docker:

  ```bash
  docker compose up
  ```

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

---

# 🏗 System Architecture

The system consists of multiple independent microservices.

Each service:

* has its own database
* can be deployed independently
* communicates via REST or Kafka

---

# 📊 Architecture Diagram

```id="9r8d0h"
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

Centralized configuration management:

* database settings
* Kafka configuration
* JWT
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

## Product Service

* product management
* stock tracking
* product status management

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

```id="4j9k2p"
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
│ ├ product-service
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
│ ├ redis
│ ├ prometheus
│ ├ grafana
│ └ zipkin
│
├ docker-compose.yml
└ README.md
```

---

# ▶️ Build the Project

```bash id="q7c2d1"
mvn clean install
```

---

# 🐳 Run the System

```bash id="a1n6x5"
docker compose up
```

All services will start automatically.

---

# 🌐 Useful Links

* http://localhost:8761/ — Eureka
* http://localhost:8081/ — phpMyAdmin
* http://localhost:8888/actuator/health — Config Server
* http://localhost:9090 — Prometheus
* http://localhost:9411 — Zipkin
* http://localhost:3000 — Grafana

---

# 🧪 Testing via PowerShell

## Product Service

```powershell id="z8f3w1"
Invoke-RestMethod -Method POST -Uri "http://localhost:8088/products/new-product" `
-Headers @{Authorization="Bearer $token"} `
-ContentType "application/json" `
-Body '{"name": "First test product", "description": "Description product", "stock": "10", "price": "3.99", "status": "ACTIVE"}'
```

```powershell id="u6d2s4"
Invoke-RestMethod -Method GET -Uri "http://localhost:8088/products/get-all-products" `
-Headers @{Authorization="Bearer $token"}
```

```powershell id="m9p5r7"
Invoke-RestMethod -Method GET -Uri "http://localhost:8088/products/get-product/1" `
-Headers @{Authorization="Bearer $token"}
```

```powershell id="l2x8q6"
Invoke-RestMethod -Method DELETE -Uri "http://localhost:8088/products/delete/1" `
-Headers @{Authorization="Bearer $token"}
```

```powershell id="k4v1n3"
Invoke-RestMethod -Method PUT -Uri "http://localhost:8088/products/update-product/1" `
-Headers @{Authorization="Bearer $token"} `
-ContentType "application/json" `
-Body '{"name": "Test product", "description": "Description product Test", "stock": "2", "price": "2.99", "status": "DEACTIVATE"}'
```

---

## Order Service

```powershell id="b7t9c2"
Invoke-RestMethod -Method POST -Uri "http://localhost:8088/orders/create-order" `
-Headers @{Authorization="Bearer $token"} `
-ContentType "application/json" `
-Body '{"customerId": 1, "items": [{"productId": 1, "quantity": 2},{"productId": 2,"quantity": 1}]}'
```

```powershell id="n5h3j8"
Invoke-RestMethod -Method GET -Uri "http://localhost:8088/orders/get-order/1" `
-Headers @{Authorization="Bearer $token"}
```

```powershell id="r1y6u4"
Invoke-RestMethod -Method GET -Uri "http://localhost:8088/orders/get-all-orders" `
-Headers @{Authorization="Bearer $token"}
```

```powershell id="e8q2w9"
Invoke-RestMethod -Method PUT -Uri "http://localhost:8088/orders/cancel-order/1" `
-Headers @{Authorization="Bearer $token"}
```

---

## Billing Service

```powershell id="s3p7k1"
Invoke-RestMethod -Method GET -Uri "http://localhost:8088/billing/get-invoice/1" `
-Headers @{Authorization="Bearer $token"}
```

```powershell id="t6m4x2"
Invoke-RestMethod -Method GET -Uri "http://localhost:8088/billing/get-all-invoices" `
-Headers @{Authorization="Bearer $token"}
```

```powershell id="w9d8f5"
Invoke-RestMethod -Method PUT -Uri "http://localhost:8088/billing/cancel-invoice/1" `
-Headers @{Authorization="Bearer $token"}
```

---

# 🔄 CI/CD

Implemented using GitHub Actions:

* build project
* run tests
* build Docker images
* publish containers

---

# 🚀 Future Improvements

* Kubernetes
* ELK (centralized logging)

---
