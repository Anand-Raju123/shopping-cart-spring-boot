# EcomStore - Online Shopping Cart Application

A full-featured E-Commerce web application built using **Spring Boot 3**, **Spring Security**, **Spring Data JPA**, **Thymeleaf**, and **MySQL**.

---

## 🚀 Features

* **User Features**:
  * Browse active product categories and product listings.
  * Search products by keyword or category.
  * Add products to cart and manage cart quantity.
  * Checkout & Place Orders with shipping address details and payment selection (COD / Online).
  * View order history with real-time status updates ("IN_PROGRESS", "DELIVERED", etc.).
  * User profile management and password change.

* **Admin Features**:
  * Manage product categories (Add/Edit/Delete/Toggle active status).
  * Product Management (Add/Edit/Delete/Stock management).
  * Order Management (View user orders and update order statuses).
  * Admin Profile management.

* **Security & Authentication**:
  * Spring Security role-based access control (`ROLE_ADMIN`, `ROLE_USER`).
  * Password encryption using BCrypt.
  * Custom success/failure authentication handlers.
  * Account lock mechanism for failed login attempts.

* **Automated Data Seeding**:
  * Pre-populated categories, products, and default admin/user accounts upon first boot via `DataInitializer`.

---

## 🛠️ Technology Stack

* **Backend**: Java 17+, Spring Boot 3.2.3, Spring Data JPA, Spring Security, JavaMailSender
* **Frontend**: HTML5, Thymeleaf, Bootstrap 5, FontAwesome icons
* **Database**: MySQL 8.0+
* **Build Tool**: Apache Maven (Maven Wrapper `mvnw` included)

---

## 📋 Prerequisites

1. **Java Development Kit (JDK 17 or higher)**
2. **MySQL Server (v8.0+)** running on `localhost:3306`

---

## ⚙️ Configuration & Setup

### 1. Database Configuration
Ensure MySQL service is running. Create the database `ecommerce_db` (or let the app auto-create if configured):

```sql
CREATE DATABASE IF NOT EXISTS ecommerce_db;
```

Check `src/main/resources/application.properties` for database connection credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

### 2. Running the Application

Using Maven Wrapper (Windows):
```powershell
.\mvnw.cmd spring-boot:run
```

Using Maven Wrapper (Linux/macOS):
```bash
./mvnw spring-boot:run
```

Once started, open your browser and navigate to:
👉 **[http://localhost:8080](http://localhost:8080)**

---

## 🔑 Default Login Credentials

The application automatically seeds the database with the following demo accounts on startup:

| Role | Email | Password | Access Level |
| :--- | :--- | :--- | :--- |
| **Admin** | `admin@gmail.com` | `admin` | Full Admin Dashboard & Management |
| **User** | `user@gmail.com` | `user` | Shopping Cart, Checkout, Order History |

---

## 📁 Project Structure

```
shopping-cart-spring-boot/
├── src/main/java/com/ecom/
│   ├── ShoppingCartApplication.java    # Main Spring Boot Entry Point
│   ├── config/                          # Security, Handlers & DataInitializer
│   ├── controller/                      # HomeController, UserController, AdminController
│   ├── model/                           # JPA Entities (UserDtls, Product, ProductOrder, Cart, OrderAddress)
│   ├── repository/                      # Spring Data JPA Repositories
│   ├── service/                         # Service Interfaces & Implementations
│   └── util/                            # Enums, Email Utils, Constants
└── src/main/resources/
    ├── application.properties           # Server & Database Configuration
    ├── static/                          # CSS, JS, Product Images
    └── templates/                       # Thymeleaf Views (User & Admin Portals)
```

---

## 🐛 Bug Fixes & Improvements

* **Place Order Whitelabel Error Fix**:
  * Resolved missing `Product` and `ProductRepository` dependencies in `OrderServiceImpl`.
  * Added proper `@Autowired` dependency injection for `ProductRepository` to update stock levels seamlessly upon order confirmation.
  * Fixed cart reset logic to execute after all order items are successfully created and persisted.
