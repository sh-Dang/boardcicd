# Board CICD Project 🚀

CI/CD 파이프라인 구축 및 CRUD 기능을 포함한 연습용 게시판 실습 프로젝트입니다. Spring Boot 기반의 백엔드와 React 기반의 프론트엔드로 구성되어 있으며, Docker를 활용한 컨테이너화 및 GitHub Actions를 통한 자동 배포를 실습합니다.

---

## 🛠 Tech Stack

### Backend
- **Framework**: Spring Boot 3.2.5
- **Language**: Java 21
- **Database**: MySQL (JPA/Hibernate)
- **Security**: Spring Security, JWT (JSON Web Token)
- **Build Tool**: Gradle

### Frontend
- **Framework**: React 19 (Vite)
- **State/Routing**: React Router 7
- **HTTP Client**: Axios
- **Styling**: CSS (Vanilla)

### Infrastructure & DevOps
- **Container**: Docker, Docker Compose
- **CI/CD**: GitHub Actions
- **Registry**: GHCR (GitHub Container Registry) / Docker Hub
- **Cloud**: Oracle Cloud Infrastructure (OCI)
- **Deployment**: Ubuntu Server on OCI VM Instance via SSH

---

## 📂 Project Structure

```text
C:\boardcicd
├── backend/            # Spring Boot Application
├── frontend/           # React Application
├── .github/workflows/  # CI/CD Pipeline Config
├── docker-compose.yml  # Local/Server Infrastructure
└── README.md
```

---

## ✨ Key Features

- **회원가입 & 로그인**
  - JWT 기반 인증 시스템 구현
  - Email 식별자 기반 로그인 처리

- **게시판 CRUD**
  - 게시글 작성(Create)
  - 게시글 조회(Read)
  - 게시글 수정(Update)
  - 게시글 삭제(Delete)

- **인가(Authorization)**
  - 로그인 사용자만 게시글 작성 가능
  - 본인 게시글만 수정 및 삭제 가능

- **CI/CD 자동화**
  - `main` 브랜치 Push 시 GitHub Actions 실행
  - 백엔드/프론트엔드 Docker 이미지 자동 빌드
  - GHCR 또는 Docker Hub에 이미지 Push
  - OCI Ubuntu VM Instance로 SSH 자동 배포
  - 서버에서 `docker-compose pull` 및 `docker-compose up -d` 실행

---

## 🏗 Architecture

```text
GitHub
   ↓ Push (main branch)

GitHub Actions
   ├─ Backend Docker Build
   ├─ Frontend Docker Build
   ├─ Push Docker Image to GHCR
   ↓

OCI Ubuntu Server
   ├─ docker-compose pull
   ├─ docker-compose up -d
   ↓

Running Containers
   ├─ Spring Boot Backend
   ├─ React Frontend
   └─ MySQL
```

---

## ☁ Infrastructure

- Oracle Cloud Infrastructure(OCI) Free Tier 사용
- Ubuntu 기반 VM Instance 운영
- Docker Compose 기반 컨테이너 환경 구성
- GitHub Actions + SSH 자동 배포 구성
- 컨테이너 기반 Full Stack 서비스 운영

---

## 🚀 Getting Started

### Prerequisites

- Java 21
- Node.js (v18+)
- Docker
- Docker Compose

---

## 💻 Local Development

### 1. Backend

```bash
cd backend
./gradlew bootRun
```

### 2. Frontend

```bash
cd frontend
npm install
npm run dev
```

### 3. Docker Compose (Full Stack)

```bash
docker-compose up -d
```

---

## ⛓ CI/CD Pipeline

이 프로젝트는 GitHub Actions를 사용하여 CI/CD를 자동화합니다.

### 1. Build & Push

- `main` 브랜치 Push 발생 시 Workflow 실행
- Backend / Frontend Docker 이미지 빌드
- GHCR(GitHub Container Registry) 또는 Docker Hub에 이미지 Push

### 2. Deploy

- GitHub Actions에서 OCI Ubuntu VM으로 SSH 접속
- 최신 Docker 이미지 Pull
- `docker-compose up -d` 실행
- 최신 컨테이너 기반으로 서비스 재배포