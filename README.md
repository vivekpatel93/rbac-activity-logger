Backend Developer Assessment
Role-Based Access Control (RBAC) & Session Management System

This project is a small web application that demonstrates Role-Based Access Control (RBAC), Session Management, and Activity Logging, along with a simple frontend interface.

It was built as part of the Backend Developer Assessment challenge.

🚀 Features
1️⃣ Role-Based Access Control (RBAC) =>

The application supports three user roles:

 *Admin

 *Create new users

*Assign roles

*View all activity logs

*Manager

*View activity logs

Cannot assign roles

User

Login and interact with frontend buttons

Cannot view logs

All roles and permissions are enforced on protected backend routes.

2️⃣ Authentication & Session Management

Secure login & logout system

Session-based authentication (JWT / Server-side sessions)

Sessions expire after 15 minutes of inactivity

Protected routes using middleware

Passwords encrypted using bcrypt (if implemented)

3️⃣ Activity Logging

The frontend contains two buttons:

Button A

Button B

When clicked by an authenticated user, the system logs:

User ID

User Role

Button Clicked

Timestamp

Logs are stored in the database and accessible based on role:

Role	View Logs	Modify Logs
Admin	✅ Yes	❌ No
Manager	✅ Yes	❌ No
User	❌ No	❌ No
4️⃣ Frontend

A minimal frontend built with:

HTML

CSS (basic)

Vanilla JavaScript

Features:

Login

Logout

Button A

Button B

API calls to backend for logging activity

🛠 Tech Stack

Backend

Node.js / Express.js (or your backend framework)

JWT / Express-session

bcrypt (for password hashing)

Database

SQLite / PostgreSQL / MongoDB (mention the one you used)

Frontend

HTML

CSS

JavaScript (Fetch API)

Optional

Docker (if implemented)

📂 Project Structure
backed Structure
com.vivek.rbac

│__ config
|   |-CorsConfig
|    |-DataLoader
├── controller
│   ├── AuthController.java
│   ├── UserController.java
│   └── LogController.java
│
├── service
│   ├── AuthService.java
│   ├── UserService.java
│   └── LogService.java
│
├── repository
│   ├── UserRepository.java
│   └── ActivityLogRepository.java
│
├── entity
│   ├── User.java
│   └── ActivityLog.java
│
├── dto
│   ├── LoginRequestDTO.java
    |-- UserRegister.java
│   ├── UserResponse.java
│   ├── LogDTO.java
│   └── AuthResponseDTO.java
│
├── security
│   ├── JwtUtil.java
│   ├── JwtFilter.java
│   └── SecurityConfig.java
│
└── RbacApplication.java


forntend Structure
src/
 ├── api/
 │    └── api.js
 │
 ├── components/
 │    ├── Login.jsx
 │    ├── Dashboard.jsx
 │    └── Logs.jsx
 │
 ├── utils/
 │    └── jwt.js
 │
 ├── App.jsx
 ├── main.jsx
 └── index.css



⚙️ Setup Instructions
1️⃣ Clone the Repository
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name

2️⃣ Install Dependencies
npm install

3️⃣ Configure Environment Variables

Create a .env file in the root directory:

PORT=5000
JWT_SECRET=your_secret_key
SESSION_SECRET=your_session_secret
DATABASE_URL=your_database_url

4️⃣ Setup Database

If using SQLite:

npm run migrate


If using PostgreSQL:

Create database

Run schema.sql

5️⃣ Start the Application
npm start


Backend will run at:

http://localhost:5000

👥 Example Users for Testing
Role	Email	Password
Admin	admin@example.com
	admin123
Manager	manager@example.com
	manager123
User	user@example.com
	user123

(Passwords are hashed in the database.)

🔐 Session Behavior =>

Sessions expire after 15 minutes of inactivity

Middleware checks:

Authentication

Authorization (role validation)

Unauthorized access returns:

401 Unauthorized

403 Forbidden

