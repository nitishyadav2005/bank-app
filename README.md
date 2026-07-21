# 🏦 Bank App

A console-based **Bank Management System** built using **Core Java** and **Object-Oriented Programming (OOP)** principles. This project simulates basic banking operations such as account creation, deposits, withdrawals, fund transfers, and transaction history through a command-line interface.

---

## 📌 Features

- 👤 Create a new customer account
- 🔍 Search customer accounts
- 💰 Deposit money
- 💸 Withdraw money
- 🔄 Transfer funds between accounts
- 📄 View account details
- 📜 Transaction history
- ✅ Input validation and error handling
- 🏗️ Modular OOP-based architecture

---

## 🛠️ Tech Stack

- Java
- Core Java
- Object-Oriented Programming (OOP)
- Collections Framework
- File Structure using Packages
- IntelliJ IDEA

---

## 📂 Project Structure

```text
src
├── app
│   └── Main.java
│
├── domain
│   ├── Account.java
│   ├── Customer.java
│   ├── Transaction.java
│   └── Type.java
│
├── exception
│   ├── AccountNotFoundException.java
│   ├── InsufficientFundsException.java
│   └── ValidationException.java
│
├── repository
│   ├── AccountRepository.java
│   ├── CustomerRepository.java
│   └── TransactionRepository.java
│
├── service
│   ├── BankService.java
│   └── impl
│       └── BankServiceImpl.java
│
└── util
    └── Validation.java
```
## 🏗️ Architecture

- **app** – Entry point of the application.
- **domain** – Contains entity classes such as `Account`, `Customer`, and `Transaction`.
- **repository** – Handles in-memory data storage and retrieval.
- **service** – Defines business logic through interfaces and implementations.
- **exception** – Contains custom exceptions for better error handling.
- **util** – Utility classes for input validation and common helper methods.

---

## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/nitishyadav2005/bank-app.git
```

### Navigate to the Project

```bash
cd bank-app
```

### Compile

```bash
javac -d out src/**/*.java
```

### Run

```bash
java Main
```

> Or simply open the project in IntelliJ IDEA and run `Main.java`.

---

## 📚 Concepts Practiced

- Object-Oriented Programming
- Encapsulation
- Abstraction
- Collections (ArrayList, HashMap)
- Exception Handling
- Package Organization
- Clean Code Practices

---



## 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Push the branch
5. Open a Pull Request

---

## 👨‍💻 Author

**Nitish Yadav**

- GitHub: https://github.com/nitishyadav2005

---

## ⭐ Support

If you found this project helpful, consider giving it a **⭐ Star** on GitHub.
