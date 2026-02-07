# Attendance Management System - Backend

A robust Spring Boot REST API for managing student attendance records with real-time updates and comprehensive data management capabilities.

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Examples](#api-examples)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

- **Create Attendance Records** - Add new student attendance entries
- **Read Attendance Data** - Retrieve all or specific attendance records
- **Update Records** - Modify existing attendance information
- **Delete Records** - Remove attendance records from the system
- **Attendance Percentage Calculation** - Automatic calculation of attendance percentage
- **CORS Support** - Cross-origin resource sharing for frontend integration
- **H2 Database** - In-memory database for development and testing
- **RESTful API** - Clean and standardized REST endpoints
- **JPA/Hibernate** - Object-relational mapping for database operations

## 🛠 Tech Stack

- **Framework**: Spring Boot 4.1.0-M1
- **Language**: Java 17
- **Database**: H2 (In-Memory)
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **Server**: Apache Tomcat 11.0.15
- **API**: RESTful Web Services

## 📁 Project Structure

```
attendance-backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/attendance/
│   │   │   ├── AttendanceApplication.java      # Main application class
│   │   │   ├── config/
│   │   │   │   └── CorsConfig.java             # CORS configuration
│   │   │   ├── controller/
│   │   │   │   └── AttendanceController.java   # REST endpoints
│   │   │   ├── entity/
│   │   │   │   └── Attendance.java             # JPA entity model
│   │   │   ├── repository/
│   │   │   │   └── AttendanceRepository.java   # Data access layer
│   │   │   └── service/
│   │   │       └── AttendanceService.java      # Business logic
│   │   └── resources/
│   │       ├── application.properties          # Application config
│   │       └── static/                         # Static files
│   └── test/
│       └── java/com/example/attendance/
│           └── AttendanceApplicationTests.java # Test cases
├── screenshot/                                  # Screenshots and visuals
├── pom.xml                                      # Maven dependencies
├── mvnw / mvnw.cmd                             # Maven wrapper scripts
└── Dockerfile                                   # Docker configuration
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use the included Maven wrapper)
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd attendance-backend
   ```

2. **Build the project**
   ```bash
   ./mvnw clean install
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on **http://localhost:8080**

## 📡 API Endpoints

### Base URL
```
http://localhost:8080/attendance
```

### Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/attendance` | Retrieve all attendance records |
| POST | `/attendance` | Create a new attendance record |
| PUT | `/attendance/{id}` | Update an attendance record |
| DELETE | `/attendance/{id}` | Delete an attendance record |

### Response Model

```json
{
  "id": 1,
  "studentName": "John Doe",
  "totalClasses": 50,
  "attendedClasses": 45,
  "percentage": 90.0,
  "status": "Present"
}
```

## 🗄 Database

### H2 Console Access

Access the H2 database console at:
```
http://localhost:8080/h2-console
```

**Connection Details:**
- URL: `jdbc:h2:mem:testdb`
- Username: `SA`
- Password: (leave blank)

### Database Schema

**Table: attendance**

| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key (auto-generated) |
| student_name | VARCHAR(255) | Name of the student |
| total_classes | INTEGER | Total number of classes |
| attended_classes | INTEGER | Number of classes attended |
| percentage | FLOAT | Attendance percentage |
| status | VARCHAR(255) | Current status (e.g., Present) |

## ⚙️ Configuration

Edit `src/main/resources/application.properties` to customize settings:

```properties
# Server Configuration
spring.application.name=attendance
server.port=8080

# H2 Database
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 💻 Running the Application

### Using Maven Wrapper (Recommended)

**Windows:**
```bash
mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

### Using Maven (if installed globally)

```bash
mvn spring-boot:run
```

### Using Docker

Build and run with Docker:
```bash
docker build -t attendance-backend .
docker run -p 8080:8080 attendance-backend
```

## 📚 API Examples

### Create Attendance Record

**Request:**
```bash
curl -X POST http://localhost:8080/attendance \
  -H "Content-Type: application/json" \
  -d '{
    "studentName": "John Doe",
    "totalClasses": 50,
    "attendedClasses": 45,
    "percentage": 90.0,
    "status": "Present"
  }'
```

**Response:**
```json
{
  "id": 1,
  "studentName": "John Doe",
  "totalClasses": 50,
  "attendedClasses": 45,
  "percentage": 90.0,
  "status": "Present"
}
```

### Get All Records

**Request:**
```bash
curl http://localhost:8080/attendance
```

**Response:**
```json
[
  {
    "id": 1,
    "studentName": "John Doe",
    "totalClasses": 50,
    "attendedClasses": 45,
    "percentage": 90.0,
    "status": "Present"
  }
]
```

### Update Record

**Request:**
```bash
curl -X PUT http://localhost:8080/attendance/1 \
  -H "Content-Type: application/json" \
  -d '{
    "studentName": "John Doe",
    "totalClasses": 50,
    "attendedClasses": 48,
    "percentage": 96.0,
    "status": "Present"
  }'
```

### Delete Record

**Request:**
```bash
curl -X DELETE http://localhost:8080/attendance/1
```

## 📸 Screenshots

Screenshots demonstrating the backend functionality can be found in the `/screenshot` folder:

- `output.png` - Application output and functionality demonstration

## 🔌 CORS Configuration

The application is configured to accept requests from:
- `http://localhost:3000`
- `http://localhost:3001`
- `http://localhost:5173`

To add more origins, update `CorsConfig.java`:

```java
registry.addMapping("/**")
    .allowedOrigins("http://your-frontend:port")
    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
    .allowedHeaders("*")
    .allowCredentials(true)
    .maxAge(3600);
```

## 🧪 Testing

Run unit tests:
```bash
./mvnw test
```

## 📖 Dependencies

Key dependencies included:
- Spring Boot Web
- Spring Data JPA
- H2 Database
- Spring Boot DevTools
- Hibernate Core

See `pom.xml` for complete dependency list.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For issues and questions:
- Create an issue in the repository
- Contact the development team

## 📅 Version History

- **v0.0.1-SNAPSHOT** - Initial release with basic CRUD operations

---

**Last Updated:** February 2026

**Status:** Active Development
