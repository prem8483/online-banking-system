🏦 Online Banking System

A full-stack Online Banking System built using Spring Boot (Java) and HTML, CSS, JavaScript that simulates real-world banking operations such as user registration, account management, money transfer, and transaction tracking.

This project is designed following real banking flow, clean architecture, and industry practices like layered structure, DTOs, transactional safety, and Git version control.

🚀 Features
👤 User Features

User registration & login

View profile and update details

View linked bank accounts

Send money to another account

View transaction history (Send / Receive)

Nominee management

Secure logout

🛠️ Admin Features

Admin login

View all users

Block / unblock users

View all bank accounts

View all transactions

Approve / reject account applications

Edit user profiles

💸 Transaction Features

Secure money transfer

Balance validation

Sender → DEBIT transaction

Receiver → CREDIT transaction

Atomic transfer using @Transactional

Proper transaction history per account

🧱 Project Architecture
online_bank_system
│
├── controller        # REST Controllers
├── service           # Business logic
├── repository        # JPA repositories
├── model             # Entity classes
├── dto               # Data Transfer Objects
│
├── resources
│   ├── application.properties
│   └── static        # Frontend (HTML, CSS, JS)
│
└── pom.xml


Architecture follows:

Controller → Service → Repository

Clean separation of concerns

DTO-based request handling

🛠️ Technologies Used
Backend

Java

Spring Boot

Spring Data JPA

Hibernate

REST APIs

Transaction Management (@Transactional)

Frontend

HTML

CSS

JavaScript (Fetch API)

LocalStorage for session handling

Database

PostgreSQL (can be replaced with MySQL)

Tools

Maven

Git & GitHub

VS Code / IntelliJ

Postman

🗃️ Database Design (Important Tables)

users

accounts

transactions

nominees

admins

application_forms

login_history

Transactions Table Logic
Column	Purpose
account_no	User’s account
txn_type	SEND / RECEIVE
amount	Transaction amount
related_account_number	Opposite account
description	Meaningful message
created_at	Timestamp
🔁 Money Transfer Flow (Real-World Logic)

Validate sender & receiver accounts

Check sender balance

Debit sender account

Credit receiver account

Save 2 transaction records

Sender → SEND

Receiver → RECEIVE

Commit atomically using @Transactional

✔️ If any step fails → rollback happens automatically

🔐 Security & Validation

Input validation on backend

Account status checks (active / blocked)

Balance checks before transfer

Transaction rollback on failure

Session handled via browser localStorage

📌 API Highlights
Method	Endpoint	Description
POST	/api/users/login	User login
GET	/api/accounts/user/{userId}	Get user accounts
POST	/api/transfer	Transfer money
GET	/api/transactions/account/{accountNo}	Transaction history
POST	/api/admin/login	Admin login
🧪 How to Run the Project
1️⃣ Clone Repository
git clone https://github.com/prem8483/online-banking-system.git

2️⃣ Configure Database

Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=postgres
spring.datasource.password=your_password

3️⃣ Run Application
mvn spring-boot:run

4️⃣ Open Browser
http://localhost:8080

📈 Git & Version Control

This project uses:

Feature-based commits

Clean commit messages

GitHub for remote repository

Industry-standard workflow

Example commit:

Add transactional money transfer with debit and credit entries

🎓 Learning Outcomes

Real-world banking logic

Spring Boot layered architecture

REST API design

Database relationships

Transaction handling

Git & GitHub workflow

Debugging real bugs

👨‍💻 Author

Prem Kadam
B.Sc Computer Science
📍 Ahmednagar (Ahilyanagar), India

🔗 GitHub: https://github.com/prem8483

⭐ Future Enhancements

JWT authentication

Password encryption

Pagination for transactions

Email notifications

Role-based access

Microservices split
