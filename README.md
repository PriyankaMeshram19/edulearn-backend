# 📚 EduLearn — Full-Stack E-Learning Platform (Backend)

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![JWT](https://img.shields.io/badge/JWT-Auth-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 🚀 Live Demo

- 🌐 Frontend: [edulearn-frontend-5hoo.vercel.app](https://edulearn-frontend-5hoo.vercel.app)
- ⚙️ Backend API: [edulearn-backend-m8ov.onrender.com](https://edulearn-backend-m8ov.onrender.com)
- 💻 Frontend Repo: [github.com/PriyankaMeshram19/edulearn-frontend](https://github.com/PriyankaMeshram19/edulearn-frontend)

---

## 📌 About Project

**EduLearn** is a full-stack e-learning platform, inspired by platforms like Udemy, built end-to-end as a portfolio project — from database design to production deployment.

Students can browse courses, purchase them through a simulated payment gateway, watch course videos, and track their learning progress. Admins can manage the full course catalog and view enrollment analytics — all backed by a secure, role-based REST API.

---

## ✨ Features

### Student Features
- 🔐 JWT Authentication (Register / Login / Forgot Password / Reset Password)
- 📧 Email notifications — welcome email, password reset link, payment receipt
- 📚 Browse published courses (dynamic, database-driven)
- 💳 Payment — Card, UPI, Net Banking, QR Code (simulated gateway)
- 🎥 Course Player — embedded YouTube video + notes
- ✅ Scroll-gated "Mark as Completed" progress tracking
- 📊 Student Dashboard — purchased courses, completion status
- 👤 Profile — view and update name

### Admin Features
- 🛡️ Role-Based Access Control (Student vs Admin, enforced server-side)
- 📚 Manage Courses — Add, Edit, Delete, Publish/Draft
- 👥 Manage Students — view all registered students
- 📊 Dashboard — platform-wide stats (total courses, students, enrollments)
- 📈 Per-Course Enrollment View — which students enrolled in which course, with completion status

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 21 | Core language |
| Spring Boot | 3.x | Framework |
| Spring Security | 6.x | Authentication & Authorization |
| JWT (jjwt) | 0.12.x | Stateless token auth |
| Hibernate / JPA | — | ORM |
| MySQL | 8.0 | Database |
| JavaMailSender + SendGrid | — | Transactional email |
| Maven | — | Build tool |
| Docker | — | Containerized deployment |

---

## 📁 Project Structure

edulearn-backend/
├── src/main/java/com/edulearn/backend/
│   ├── config/          # Security & CORS
│   ├── controller/      # REST API controllers
│   ├── dto/             # Request/response DTOs
│   ├── entity/          # JPA entities
│   ├── repository/      # JPA repositories
│   ├── security/        # JWT authentication
│   ├── service/         # Business logic
│   ├── service/impl/    # Service implementations
│   └── EduLearnApplication.java
├── src/main/resources/
│   └── application.properties
├── Dockerfile
├── pom.xml
├── .gitignore
└── README.md

## 🗄️ Database Schema

| Table | Columns |
|-------|---------|
| `users` | id, name, email, password, role, created_at |
| `courses` | id, title, author_name, description, thumbnail_url, price, youtube_video_url, documentation_content, status, created_at |
| `enrollments` | id, student_id, course_id, completed, enrolled_at, completed_at |
| `payments` | id, user_id, course_id, payment_method, transaction_id, amount, status, created_at |
| `password_reset_tokens` | id, token, user_id, expiry_date |

## 🔐 API Endpoints

### Auth APIs (Public)

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/auth/register` | Register a new student | Public |
| POST | `/api/auth/login` | Login, returns JWT | Public |
| POST | `/api/auth/forgot-password` | Send password reset link | Public |
| POST | `/api/auth/reset-password` | Reset password using token | Public |

### Course APIs

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/courses` | All published courses | Public |
| GET | `/api/courses/{id}` | Get single course | Public |
| GET | `/api/admin/courses` | All courses including drafts | Admin |
| POST | `/api/admin/courses` | Create course | Admin |
| PUT | `/api/admin/courses/{id}` | Update course | Admin |
| DELETE | `/api/admin/courses/{id}` | Delete course | Admin |

### Enrollment APIs

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/enrollments` | Enroll in a course | Student |
| GET | `/api/enrollments/student` | Get my enrollments | Student |
| GET | `/api/enrollments/{id}` | Get single enrollment (Course Player) | Student |
| PATCH | `/api/enrollments/{id}/complete` | Mark course as completed | Student |
| GET | `/api/admin/courses/{courseId}/enrollments` | Get enrolled students | Admin |

### Payment APIs

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/payment/simulate` | Process simulated payment and trigger enrollment | Student |

### Admin APIs

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/admin/stats` | Platform-wide statistics | Admin |
| GET | `/api/admin/students` | Get all registered students | Admin |

### Profile APIs

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/profile` | Get logged-in user's profile | Authenticated |
| PUT | `/api/profile` | Update profile name | Authenticated |

---

## ⚙️ Setup & Run Locally

### Prerequisites

- Java 21
- MySQL 8.0
- Maven

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/PriyankaMeshram19/edulearn-backend.git
cd edulearn-backend

# 2. Create the database
mysql -u root -p
CREATE DATABASE elearning_db;

# 3. Configure application.properties
# Add your database credentials, JWT secret, and mail settings

# 4. Run the application
./mvnw spring-boot:run
```

The API will be available at:

`http://localhost:8080`

---

## 🚢 Deployment

- **Backend:** Deployed as a Docker container on Render
- **Database:** Hosted on Railway (MySQL)
- **Environment Variables:** DB credentials, JWT secret, and mail API key are securely injected
- **Security:** Secrets are never committed to source control
- **CORS:** Restricted to the deployed frontend origin

## 📸 Screenshots
### Landing Page
<img src="https://github.com/PriyankaMeshram19/edulearn-backend/raw/main/screenshots/1-landing-page.png" width="800"/>
