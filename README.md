# Computers Shop API

## Description
This project is a **Spring Boot REST API** for managing a shop that sells computers. The API allows you to:

- Manage stores (CRUD operations).
- Manage computers assigned to specific stores (CRUD operations).

The system is built using a **layered architecture** that includes controllers, services, DTOs, and models, ensuring clean separation of concerns.

---

## Features

- **Store Management:**
  - Add, update, retrieve, and delete stores.
  - View the inventory of computers in a specific store.

- **Computer Management:**
  - Add, update, retrieve, and delete computers linked to a specific store.
  - Search for computers by ID or within a store's inventory.

---
## Diagrama UML de clases
   
  <pre>
+----------------+            +----------------+           +----------------+
|     Store      |1         * |    Computer    |*         1 |   StoreDTO     |
|----------------|------------|----------------|-----------|----------------|
| - id: Long     |            | - id: Long     |           | - id: Long     |
| - name: String |            | - name: String |           | - name: String |
| - owner: String|            | - specs: String|           | - owner: String|
| - taxId: String|            | - price: Double|           | - taxId: String|
+----------------+            +----------------+           +----------------+
        |                           ^                             ^
        |1                          |*                            |1
        |                           |                             |
        v                           v                             |
+------------------+         +-------------------+          +-------------------+
| StoreService     |         | ComputerService   |          | ComputerDTO       |
|------------------|         |-------------------|          |-------------------|
| + create()       |         | + create()        |          | - id: Long        |
| + update()       |         | + update()        |          | - name: String    |
| + delete()       |         | + delete()        |          | - specs: String   |
| + findById()     |         | + findById()      |          | - price: Double   |
| + findAll()      |         | + findAll()       |          +-------------------+
+------------------+         +-------------------+
        ^                            ^
        |                            |
        v                            v
+-----------------+          +-------------------+
| StoreController |          | ComputerController |
|-----------------|          |-------------------|
| + create()      |          | + create()         |
| + update()      |          | + update()         |
| + delete()      |          | + delete()         |
| + findById()    |          | + findById()       |
| + findAll()     |          | + findAll()        |
+-----------------+          +-------------------+
</pre>


## Project Structure

```
├── src/main/java/com/example/computershop
│   ├── controller
│   │   ├── ComputerController.java
│   │   └── StoreController.java
│   ├── dto
│   │   ├── ComputerDTO.java
│   │   └── StoreDTO.java
│   ├── model
│   │   ├── Computer.java
│   │   └── Store.java
│   ├── repository
│   │   ├── ComputerRepository.java
│   │   └── StoreRepository.java
│   ├── service
│   │   ├── ComputerService.java
│   │   └── StoreService.java
│   └── ComputersShopApplication.java
├── src/test/java/com/example/computershop
│   ├── ComputerServiceTest.java
│   └── StoreServiceTest.java
└── pom.xml
```

---

## Technologies Used

- **Java 17**
- **Spring Boot** (version 3.x)
- **Maven**
- **H2 Database** (for development and testing)
- **JPA/Hibernate** (for ORM)
- **Postman** (for API testing)
- **JUnit** (for unit testing)

---

## API Endpoints

### Store Endpoints

| Method | Endpoint                | Description                     |
|--------|-------------------------|---------------------------------|
| GET    | `/api/stores`           | Retrieve all stores             |
| GET    | `/api/stores/{id}`      | Retrieve a store by ID          |
| POST   | `/api/stores`           | Create a new store              |
| PUT    | `/api/stores/{id}`      | Update an existing store        |
| DELETE | `/api/stores/{id}`      | Delete a store by ID            |

### Computer Endpoints

| Method | Endpoint                            | Description                            |
|--------|-------------------------------------|----------------------------------------|
| GET    | `/api/computers/store/{storeId}`    | Retrieve all computers in a store      |
| GET    | `/api/computers/{id}`               | Retrieve a computer by ID              |
| POST   | `/api/computers/store/{storeId}`    | Add a new computer to a specific store |
| PUT    | `/api/computers/{id}`               | Update an existing computer            |
| DELETE | `/api/computers/{id}`               | Delete a computer by ID                |

---

## How to Run the Project

### Prerequisites

- Install **Java 17**.
- Install **Maven**.
- Install an **IDE** like IntelliJ or Visual Studio Code.

### Steps to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo/computers-shop.git
   cd computers-shop
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API Documentation:**
   - Swagger UI: `http://localhost:8080/swagger-ui/index.html` (if Swagger is configured).

5. **Test the API:**
   - Use **Postman** to send requests to the endpoints.

---

## Database

The application uses an **H2 in-memory database**. You can access the H2 console at:

- URL: `http://localhost:8080/h2-console`
- Default credentials:
  - **Username:** `sa`
  - **Password:** (leave blank)

---

## Testing

- Unit tests are written using **JUnit**.
- Run the tests with the following command:
  ```bash
  mvn test
  ```
- Test coverage is expected to be **70% or higher**.

---

- Ejemplo de captura de cobertura:
<img src="/Foto/Imagen pegada.png" alt="Captura de cobertura de pruebas" />

## Future Enhancements

- Implement Swagger for API documentation.
- Add pagination and filtering for the endpoints.
- Integrate with a production-ready database like MySQL or PostgreSQL.
- Add authentication and authorization using Spring Security.

---

## Contributo

- *FUAD AL KWKABANI* <li><a href="https://github.com/Fuad-Alkwkabani">GitHub</a></li>
---


