# Boat Management Application

This application is designed for managing boats, allowing users to register, log in, and perform various operations on boat records. The application is comprised of a frontend Angular application and a backend Spring Boot service, with PostgreSQL used as the database.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Running the Application](#running-the-application)
- [Initial Data](#initial-data)

## Technologies Used

- **Frontend**:
  - Angular (for building the user interface)
  - RxJS (for reactive programming)
  - Bootstrap (for responsive design)
- **Backend**:
  - Spring Boot (for building RESTful APIs)
  - Spring Data JPA (for database interactions)
- **Database**:
  - PostgreSQL (for data storage)
- **Containerization**:
  - Docker Compose (for running the application in containers)

## Project Structure

```
/frontend       // Contains the Angular frontend application
  ├── src/      // Source files for Angular application
  └── ...

/backend        // Contains the Spring Boot backend application
  ├── src/      // Source files for Spring Boot application
  └── ...

/docker-compose.yml // Configuration for running the application with Docker
```

## Setup Instructions

### Prerequisites

- Install Docker and Docker Compose.
- Ensure you have Node.js and npm (for the Angular frontend).
- Ensure you have Java 21 (for the Spring Boot backend).
- Have PostgreSQL installed and running if you plan to run the database locally (though using Docker Compose is recommended).

### Running the Application with Docker

0. Creating the `.env` File

To configure your Docker Compose environment variables, create a `.env` file in the root directory of your project (where your `docker-compose.yml` is located). This file will contain the necessary environment variables for your backend and database services.

Here is an example structure of the `.env` file:

```properties
# Database Configuration
DB_USER=my_database_user
DB_PASSWORD=my_database_password
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/boatapp
POSTGRES_DB=boat_management
POSTGRES_USER=my_database_user
POSTGRES_PASSWORD=my_database_password

# JWT Configuration
JWTSECRET=my_secret_key
JWT_EXPIRATION=3600 # expiration time in seconds
```

Replace `my_database_user`, `my_database_password`, `my_secret_key`, and other placeholder values with the actual values you want to use for your application.

Make sure to adjust the values based on your project's requirements.

### Generating a 32-Character Hash with OpenSSL

To generate a secure random string (32 hash) for use as a secret key (e.g., for JWT), you can use the following command in your terminal:

```bash
openssl rand -base64 32
```

This command generates a 32-character hexadecimal string (

2. Build and run the Docker containers using:

   ```bash
   docker-compose up --build
   ```

3. The application will be up and running at:

   - **Frontend**: `http://localhost:4200`
   - **Backend**: `http://localhost:8080`

using 16 bytes of randomness).

### Running Without Docker

If you prefer to run the applications separately without Docker, follow these steps:

#### 1. Initialize the Database

- Make sure PostgreSQL is running.
- Create a database named `boat_management`.

#### 2. Running the Backend

- Navigate to the `/backend` directory and run the following command:
  ```bash
  ./mvnw spring-boot:run
  ```
- The backend will run on `http://localhost:8080`.

#### 3. Running the Frontend

- Navigate to the `/frontend` directory and run:
  ```bash
  npm install
  npm start
  ```
- The frontend will run on `http://localhost:4200`.

## Initial Data

Multiple boats are initialized through the `BoatDataLoader` class. When the application starts for the first time, this file populates the PostgreSQL database with sample boat data for testing purposes.

### SWAGGER API

- **Swagger API Access**: You can access the Swagger UI at `http://localhost:8080/swagger-ui/index.html`, where users can interact with and test the API endpoints. To access the authorized routes in the API documentation, you must first execute the `/users/login` endpoint to authenticate.

---

### TEST

To run the tests for this application, you can use the following commands:

#### Frontend

To run the tests for the Angular frontend application, use:

```bash
ng test
```

This command will execute your unit tests using Karma as the test runner and display the results in your terminal.

#### Backend

To run the tests for the Spring Boot backend application, use:

```bash
mvn test
```

This will execute the unit tests defined in your backend codebase and display the results in the terminal.

#### SonarQube Analysis

You can also run a Docker image of SonarQube and then perform a quality analysis of your codebase using the following commands:

1. **Run SonarQube Docker Image**:

   ```bash
   docker run -d --name sonarqube -p 9000:9000 sonarqube
   ```

2. **After SonarQube is running**, execute the following command to perform a clean build and trigger the Sonar analysis (make sure to replace with your actual SonarQube token):

   ```bash
   mvn clean verify sonar:sonar \
   -Dsonar.projectKey=Boat-App \
   -Dsonar.projectName='Boat App' \
   -Dsonar.host.url=http://localhost:9000 \
   -Dsonar.token=<your-token>
   ```

This command will clean and package your application, run tests, and send the results to your SonarQube server for static code analysis.

---
