# 💡 Booking Website – Full Stack Web Application

This is a full-stack lighting system management project. It includes:

- **Backend**: Java Spring Boot (Java 17, Maven)
- **Frontend**: Node.js-based (likely React)
- **Database**: MySQL (or similar; configure in `application.properties`)

---

## 🗂️ Project Structure

```
Projects/
├── backend/   # Java Spring Boot application
└── frontend/  # Node.js frontend (React or Vue)
```

---

## ✅ Prerequisites

- Java 17+
- Maven 3.6+
- Node.js 18+
- npm
- MySQL or compatible RDBMS

---

## ⚙️ Backend Setup

### 1. Navigate to the backend folder

```bash
cd Projects/backend
```

### 2. Configure Database Connection

Edit `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/lightingsystem
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Create the database manually in MySQL:

```sql
CREATE DATABASE lightingsystem;
```

### 3. Build the project

Linux/macOS:
```bash
./mvnw clean install
```

Windows:
```bash
mvnw.cmd clean install
```

### 4. Run the Spring Boot Application

```bash
./mvnw spring-boot:run
```

The backend server runs at `http://localhost:9999`.

---

## 🌐 Frontend Setup

### 1. Navigate to the frontend folder

```bash
cd ../frontend
```

### 2. Install dependencies

```bash
npm install
```

### 3. Start the development server

```bash
npm start
```

The frontend will be available at `http://localhost:3000/login`.

### 4. Proxy Setup

In `frontend/package.json`, add:

```json
"proxy": "http://localhost:8080"
```

This forwards API calls to the backend during development.

---

## 🧪 Running Tests

### Backend Tests

```bash
./mvnw test
```

### Frontend Tests

```bash
npm test
```

---

## 📦 Production Build

To build the frontend:

```bash
npm run build
```

Copy the contents of `build/` into:

```
backend/src/main/resources/static/
```

Spring Boot will serve it as static content.

---

## 📄 License

MIT License

---

## 🙋 Support

For issues, open a ticket in the repository.
