# Dealer Vehicle Backend API

A **Spring Boot RESTful API** for managing **Dealers**, **Vehicles**, and **Payments** with **JWT authentication** and **role-based access control**.  
This backend provides secure endpoints for CRUD operations and integrates dealer subscription payments.

---

## Features

- **JWT-based authentication** (login/register)
- **Dealer management** (CRUD)
- **Vehicle management** (CRUD, dealer-wise filtering)
- **Payment management** (initiate, get all, get by ID)
- **Enum-based support** for `PaymentMethod`, `PaymentStatus`, and `VehicleStatus`
- **Layered architecture:** Controller → Service → Repository → Entity
- **Easily testable** using cURL or Postman

---

## Tech Stack

| Layer | Technology |
|--------|-------------|
| **Backend Framework** | Spring Boot |
| **Database** | MySQL / PostgreSQL |
| **Security** | Spring Security + JWT |
| **Build Tool** | Maven |
| **Java Version** | Java 17+ |
| **API Format** | REST (JSON) |

---


## Setup Instructions
### 1. Clone the Repository
```
git clone https://github.com/your-username/dealer-management.git
cd dealer-management
```

### 2. Build the Project
``` 
mvn clean install

```

### 3. Run the Application
``` 
mvn spring-boot:run

```

## Server will start at
``` 
http://localhost:3004

```

## Authentication APIs
### 1. Register Dealer
```
curl -X POST http://localhost:3004/api/auth/register \
-H "Content-Type: application/json" \
-d '{
  "username": "dealer1",
  "password": "12345"
}'
```

### 2. Login

```
curl -X POST http://localhost:3004/api/auth/login \
-H "Content-Type: application/json" \
-d '{
  "username": "dealer1",
  "password": "12345"
}'
```


Note: Use the returned JWT token in the Authorization header for secured routes.

## Dealer APIs
### 1. Create Dealer
```
curl -X POST http://localhost:3004/api/dealers \
-H "Authorization: Bearer <token>" \
-H "Content-Type: application/json" \
-d '{
  "name": "Abhishek Motors",
  "email": "abhishek@example.com",
  "phone": "9876543210"
}'

```

### 2. Get All Dealers
```
curl -X GET http://localhost:3004/api/dealers
```

### 3. Get Dealer by ID

```
curl -X GET http://localhost:3004/api/dealers/1
```

### 4. Delete Dealer

```
curl -X DELETE http://localhost:3004/api/dealers/1
```

## Vehicle APIs
### 1. Create Vehicle

```
curl --location 'http://localhost:3004/api/vehicles/dealer/2' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhYmhpc2hla0BleGFtcGxlLmNvbSIsImlhdCI6MTc2Mjg1Mzc3NiwiZXhwIjoxNzYyOTQwMTc2fQ.sAErD2RD8qK86beIKeO7TwT5cghgwC1hq72iQPlKSXIPuzfxp36Kkvlfa2UL_t7u' \
--data '{
  "model": "Honda City",
  "registrationNumber": "GJ01AB1234",
  "status": "AVAILABLE"
}'
```

### 2. Get All Vehicles
```
curl -X GET http://localhost:3004/api/vehicles
```

### 3. Get Vehicle by ID
```
curl -X GET http://localhost:3004/api/vehicles/1
```

### 4. Delete Vehicle
```
curl -X DELETE http://localhost:3004/api/vehicles/1
```

## Payment APIs
### 1. Initiate Payment
```
curl -X POST http://localhost:3004/api/payment/initiate \
-H "Content-Type: application/json" \
-d '{
  "dealerId": "1",
  "amount": "1000.00",
  "method": "CREDIT_CARD"
}'
```
### 2. Get All Payments

```
curl -X GET http://localhost:3004/api/payment
```

### 3. Get Payment by ID
```
curl -X GET http://localhost:3004/api/payment/1
```

### Postman collection Link : 
```
https://drive.google.com/file/d/17pMVft1jNcPb2VU_2tTh4OoSDpUxMn9Y/view?usp=sharing
```

### Abhishek Srivastav
### Software Engineer
### Linkedin : https://www.linkedin.com/in/abhishek-srivastav-26b4a1226/
### GMail : abhisheksrivastav222@gmail.com






