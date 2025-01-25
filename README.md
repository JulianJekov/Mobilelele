# Mobilelele - Car Dealership Platform

## Overview
Mobilelele is a Spring Boot web application for a car dealership that enables users to browse, sell, and manage vehicle listings. The platform provides comprehensive vehicle management, user authentication, and an intuitive interface for both buyers and sellers.

## Features
- **User Management**:
  - Secure user registration and authentication
  - Role-based access control (Admin, User)
  - User profile management

- **Vehicle Management**:
  - Detailed vehicle listings with specifications
  - Image upload functionality

## Technologies Used
### Backend
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL Database
- Hibernate
- Maven

### Frontend
- Thymeleaf
- Bootstrap
- JavaScript
- HTML5
- CSS3

## Database Structure
- Users
- Roles
- Vehicles
- Brands
- Models
- Offers
- Images

## Getting Started

### Prerequisites
- JDK 17 or higher
- Maven 3.6+
- MySQL 8.0+

### Installation
1. Clone the repository:
```sh
git clone https://github.com/JulianJekov/Mobilelele.git
```

2. Navigate to project directory:
```sh
cd Mobilelele
```

3. Configure MySQL database:
- Create database named `mobilelele`
- Update `application.properties` with your credentials

4. Build the project:
```sh
mvn clean install
```

5. Run the application:
```sh
mvn spring-boot:run
```

6. Access at `http://localhost:8080`

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/example/mobilelele/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── util/
│   └── resources/
│       ├── static/
│       ├── templates/
│       └── application.properties
└── test/
```

## Security
- Implements Spring Security
- Password encryption
- CSRF protection
- Role-based access control

## Contributing
1. Fork the project
2. Create feature branch
3. Commit changes
4. Push to branch
5. Open Pull Request

## Contact
Julian Jekov - [GitHub Profile](https://github.com/JulianJekov)

Project Link: [https://github.com/JulianJekov/Mobilelele](https://github.com/JulianJekov/Mobilelele)
