# 📚 FaithWords Library – POC for Charity

### 🎯 Objective
Full-stack project with Java backend and React frontend, focused on lightweight execution, automated testing, and the ability to run **100% locally**, ideal for charity institutions with limited infrastructure.

---

## 📦 Tech Stack

### Backend (`faithwords-library-backend`)
- Java 17 + Spring Boot
- JUnit 5 (unit tests)
- RestAssured (integration tests)
- Selenide (E2E UI testing in Java)
- Lightweight DB: H2 (in-memory or file mode) or SQLite

### Frontend (`faithwords-library-frontend`)
- React 17 + Vite (faster build, lightweight)
- React Testing Library + Jest
- Playwright (optional E2E tests for frontend)

### CI/CD & DevOps
- Jenkins (CI/CD pipeline)
- Dockerfile (backend and frontend)
- Docker Compose (local orchestration)

---

## 🚀 Project Structure

```

📁 faithwords-library-backend
├── src/
├── docker-compose.yml
├── Dockerfile
├── Jenkinsfile
└── README.md

📁 faithwords-library-frontend
├── src/
├── docker-compose.yml
├── Dockerfile
├── Jenkinsfile
└── README.md
```

---

## 📚 Core Features
- Book registration with intent selection (for sale or for rent):
    - Field `isForSell` (boolean) in the `book` table
    - Registration UI with two `radio buttons`: "For Sale" or "For Rent"
    - Search screens:
        - **Books for Sale** → query: `WHERE isForSell = true`
        - **Books for Rent** → query: `WHERE isForSell = false`

---

## ⚙️ docker-compose.yml (Sample)
```yaml
version: "3.9"
services:
  backend:
    build: ./faithwords-library-backend
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=local
  frontend:
    build: ./faithwords-library-frontend
    ports:
      - "3000:3000"
  # optional, can use embedded H2
  db:
    image: nouchka/sqlite3
    volumes:
      - ./data:/data
```

---

## 💾 Lightweight Database
- **Dev**: `H2` in-memory
- **Persistent local use**: `H2` file mode or `SQLite`
- **Online options (free)**:
    - [Supabase](https://supabase.com)
    - [ElephantSQL](https://www.elephantsql.com)
    - [PlanetScale](https://planetscale.com)

---

## 🌐 Cheap Hosting Options (if going online)
### Frontend
- [Vercel](https://vercel.com) – Free with GitHub integration
- [Netlify](https://www.netlify.com)

### Backend
- [Render](https://render.com) – Free plan (cold start)
- [Railway](https://railway.app)
- [Fly.io](https://fly.io)

---

## 🔌 Future Ideas
- Swagger UI for API documentation
- Playwright visual tests on React page
- Local data export/import (CSV/JSON)
- Offline-first with `serviceWorker`
- Parallel testing in Docker CI

---

## ✅ Roadmap
1. ✅ Define stack (done)
2. 🔄 Create project skeleton (frontend/backend)
3. 🔄 Dockerfile + docker-compose working locally
4. 🔄 Jenkinsfile for CI/CD
5. 🔄 Automated tests (unit/integration/E2E)
6. 🔄 Start real MVP for the institution

---

### 🤝 Contributing
This project serves a charitable purpose. All suggestions, ideas and pull requests are welcome ♥
