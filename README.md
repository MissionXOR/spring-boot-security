# spring-boot-security
# Spring Boot Project with Security, Email Verification, and Login Attempt Limitation

## Overview
This project is a Spring Boot application that implements robust authentication and authorization mechanisms. The key features include:

1. **Authentication & Authorization**:
   - Role-based access control (User and Admin roles).
   - Secure login and registration functionalities.

2. **Email Verification**:
   - New users must verify their email addresses before accessing the application.

3. **Login Attempt Limitation**:
   - Users are allowed up to **3 incorrect login attempts**.
   - After 3 failed attempts, the account is disabled for **24 hours**.

## Features
- **Authentication**: Users can log in with valid credentials, and each login session is secured.
- **Authorization**: Access to resources is restricted based on user roles (e.g., Admin can manage users, while regular users have limited access).
- **Email Verification**: 
  - Upon registration, users receive an email containing a verification link.
  - Accounts are activated only after the user clicks the verification link.
- **Login Attempt Limit**:
  - On 3 consecutive incorrect login attempts, the user account is locked for 24 hours.
  - Locked accounts are automatically re-enabled after the lock duration expires.

---

## Technologies Used
- **Java**
- **Spring Boot**
  - Spring Security
  - Spring MVC
  - Spring Mail (JavaMailSender for email verification)
- **Thymeleaf** (for frontend templates)
- **MySQL** (for database management)
- **Maven** (for dependency management)
- **H2 Database** (for testing purposes)

---

## Getting Started
### Prerequisites
- Java 17 or higher
- Maven 3.8+
- MySQL Server

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/MissionXOR/spring-boot-security.git
   ```

2. Navigate to the project directory:
   ```bash
   cd spring-boot-security
   ```

3. Configure the application properties:
   - Update `src/main/resources/application.properties` with your database and email credentials.


4. Build the project:
   ```bash
   mvn clean install
   ```

5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

6. Access the application in your browser at:
   ```
   http://localhost:8080
   ```

---

## Endpoints
### Public Endpoints
- **Registration**: `/register`
- **Login**: `/login`

### Secured Endpoints
- **User Dashboard**: `/user/dashboard` (Accessible by Users)
- **Admin Dashboard**: `/admin/dashboard` (Accessible by Admins)

---

## Project Structure
```
├── src/main/java/com/yourproject
│   ├── config           # Security and application configurations
│   ├── controller       # Controllers for handling requests
│   ├── entity           # Entity classes representing database tables
│   ├── repository       # JPA Repositories for database access
│   ├── service          # Business logic and services
│   └── util             # Utility classes (e.g., email service)
├── src/main/resources
│   ├── templates        # Thymeleaf templates for views
│   ├── static           # Static resources (CSS, JS, Images)
│   └── application.properties # Configuration file
└── pom.xml              # Maven dependencies
```

---

## How It Works
### Email Verification
1. A user registers on the platform.
2. The system sends an email with a unique verification link.
3. The user clicks the link to activate their account.

### Login Attempt Limitation
1. After 3 failed login attempts, the system locks the account for 24 hours.
2. A locked account is automatically unlocked after 24 hours.

### Role-Based Access
- Users with the **ROLE_USER** role can access user-specific pages.
- Users with the **ROLE_ADMIN** role have access to administrative functions.

---

## Future Enhancements
- Add OAuth2 support for social login (Google, Facebook, etc.).
- Implement CAPTCHA for added login security.
- Introduce password reset functionality.
- Extend account lockout notifications via email.


---

## Contact
For any questions or feedback, feel free to contact:
- **Name**: MD Aminul Islam
- **GitHub**: [MissionXOR](https://github.com/MissionXOR)
