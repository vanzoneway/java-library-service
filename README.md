# REST service as a task task

This repository contains a simple REST service "Book Library" as a test task

## Table of Contents

- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [SWAGGER-Documentation](#swagger-documentation)
- [Docker](#docker)
- [Usage](#usage)



## Introduction

In this service, it is necessary to use a microservices architecture, but unfortunately I haven't worked with it yet. For the first time, I used Spring Security to generate JWT tokens and create the user authorization process. I hope to master microservices and learn more about Spring Security in detail during my internship :)

## Technologies Used

1. Spring, Boot.
2. ORM:Spring Data Jpa.
3. RDBMS: PostgreSQL.
4. ModelMapper;
5. Authentication via bearer token (Spring Security);
6. Swagger

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java (version 21 or higher)
- PostgreSQL

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/vanzoneway/java-library-service.git
    ```

2. Build the project:

Linux:

```bash
./mvnw clean install
  ```


3. Run the application:

    ```bash
    java -jar target/java-library-service-0.0.1-SNAPSHOT.jar
    ```

The application will start on `http://localhost:8080`.

## SWAGGER-Documentation

  ```http
  http://localhost:8080/swagger-ui.html
  ```

Instead of 8080 you have to write your own port, where application is started

## Docker

1. Clone repositories:
```bash
git clone https://github.com/vanzoneway/java-library-service.git
 ```
2. Modify the docker-compose.yml file (if you need another mapping ports for example)


3. Build and run the application:

- Open a terminal in the java-library-service directory.
- Run the following command:
```bash
docker-compose up -d
```
  This will build and run the application in detached mode.
  [Note] On Linux Ubuntu 22.04 write just docker compose, without "-"

## Usage

### Endpoints

1. To begin, you need to perform the registration process through the following endpoint:

```http
POST /api/v1/registration
{
    "username" : "Bob",
    "password" : "1",
    "confirmPassword" : "1",
    "email" : "Bob@gmail.com"
}
 ```

2. Next, you need to perform the authentication process and obtain a JWT Token, which we will attach to the header of each of our other requests. If you are not authorized and try to access other endpoints, you will receive a 401 error: UNAUTHORIZED.


```http
POST /api/v1/auth
{
    "username": "Bob",
    "password": "1"
}
 ```

And you will get token:

```http
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCb2IiLCJyb2xlcyI6W10sImV4cCI6MTcyNDU5MDExNiwiaWF0IjoxNzI0NTg4MzE2fQ.vHhtecK19Z1ws3dWMofWobPcVgAymd8gJD7byLLSwv0"
}
```

You can view all the other endpoints in the SWAGGER documentation.
![img](readme-images/swagger-image.png)



