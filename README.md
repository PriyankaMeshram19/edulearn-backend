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
