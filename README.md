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




![Screenshot 2025-06-17 054417](https://github.com/user-attachments/assets/05f7e90d-165d-4765-95a9-a5d69463c8d2)
![Screenshot 2025-06-17 054408](https://github.com/user-attachments/assets/d4ac9cd6-6fb0-4599-bcf0-c64d68c8f3aa)
![Screenshot 2025-06-17 054400](https://github.com/user-attachments/assets/f47ef20c-f1eb-4f6b-8b64-b0f591dcaa54)
![Screenshot 2025-06-17 054353](https://github.com/user-attachments/assets/7baa9a0e-065d-43e8-9f3a-468ac5ff4636)
![Screenshot 2025-06-17 054345](https://github.com/user-attachments/assets/28c34014-5428-4a6c-b392-e1042297ef52)




