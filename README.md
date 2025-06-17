# 🛒 Product Service

The **Product Service** is responsible for managing the product catalog in the eCommerce system. It supports dynamic filtering, pagination, and caching for efficient access to product data.

---

## ✨ Features

- ➕ **Create Products**
- 📝 **Update/Delete Products**
- 🔍 **Fetch Product Details**
- 🔎 **Dynamic Filtering**:
  - By name, category, and active status
- 📄 **Pagination**:
  - Efficiently retrieve large datasets
- ⚡ **Caching with Redis** *(optional)*
- 🧠 **Specification-based Queries** using JPA

---

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA** with **SQL Server**
- **Redis** *(optional for Spring Cache)*
- **ModelMapper**

---

## 📁 Project Structure (Simplified)

```plaintext
product-service/
├── controller/
├── dto/
├── entity/
├── repository/
├── specification/
├── service/
├── config/
├── application.yml
└── ProductServiceApplication.java






