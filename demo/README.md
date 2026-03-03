# 🔐 Login System Backend (Spring Boot)

## 📌 Project Description

This is the backend of the Full Stack Login System built using Spring Boot.

It provides REST APIs for:

* User Registration
* User Login
* Forgot Password (Email Reset Link)
* Reset Password using Token
* Profile Update

It connects with React frontend and MySQL database.

---

## 🚀 Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* Spring Mail
* Maven

---

## ⚙️ Installation and Setup

### 1. Clone repository

```bash
git clone https://github.com/yourusername/projectname.git
```

---

### 2. Open backend folder

```bash
cd backend
```

---

### 3. Configure application.properties

Update with your details:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/userdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.mail.username=yourgmail@gmail.com
spring.mail.password=yourapppassword
```

---

### 4. Run the project

Using Maven:

```bash
mvn spring-boot:run
```

Or run main class in IDE.

Runs on:

```
http://localhost:8080
```

---

## 🗄 Database Setup

Create database:

```sql
CREATE DATABASE userdb;
```

Tables will auto-create.

---

## 🔗 API Endpoints

### Register

POST

```
/api/user/register
```

---

### Login

POST

```
/api/user/login
```

---

### Forgot Password

POST

```
/api/user/send-forgot-password-email
```

---

### Reset Password

POST

```
/api/user/reset-password
```

---

### Update Profile

PUT

```
/api/user/update
```

---

## 📧 Forgot Password Flow

1. User enters email
2. Reset link sent to email
3. User clicks link
4. User resets password

---

## 🌐 Deployment

Backend deployed link:

(Add after deployment)

```
https://yourbackendlink.com
```

---

## 👨‍💻 Author

Karthikeyan B

---

## 📄 License

Educational use
