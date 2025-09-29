# ☕️ 카페 메뉴 관리 서비스

> Spring으로 CRUD를 구현하며 백엔드 개발의 핵심을 경험하고, 실무에 가까운 협업 방식을 익히는 프로젝트

---

## 프로젝트 소개
이 프로젝트는 작은 로컬 카페 **'Grids & Circles'** 의 온라인 주문 시스템을 구현하는 것을 목표로 합니다.  
고객은 웹사이트를 통해 원두 패키지를 주문하며, 시스템은 매일 전날 오후 2시부터 당일 오후 2시까지의 주문을 모아 처리합니다.  
이를 위해 메뉴 데이터를 관리할 수 있는 **CRUD(Create, Read, Update, Delete)** 기능을 Spring 기반으로 구현합니다.  

---

## 팀원 소개
| 이름   | 역할            |
|--------|-----------------|
| 전현수 | 팀장, Frontend  |
| 임병수 | 팀원, Backend   |
| 김영인 | 팀원, Backend   |

---

## 주요 기능
- 메뉴 데이터 관리 (생성, 조회, 수정, 삭제)  
- 주문 내역 저장 및 고객 식별 (이메일 기반)  
- 동일 고객의 하루 주문 합산 처리  
- 배치 로직: 오후 2시 이후 주문은 다음 날 배송 시작  

---

## 기술 스택 (예시)

## 개발 도구

| 구분      | 내용 |
|-----------|------|
| **프론트엔드** | ![React](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black) ![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white) ![Next.js](https://img.shields.io/badge/Next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white) ![TailwindCSS](https://img.shields.io/badge/TailwindCSS-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white) |
| **백엔드** | ![Spring Boot](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white) ![Spring Security](https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white) ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white) ![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white) ![JPA](https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white) |
| **데이터베이스** | ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white) ![H2](https://img.shields.io/badge/H2Database-003B57?style=for-the-badge&logo=h2&logoColor=white) |
| **협업 도구** | ![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white) ![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white) ![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white) ![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white) |
| **디자인** | ![Figma](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white) |

---

## 설치 및 실행 방법 (예시)
1. 저장소 클론  
   - `git clone https://github.com/organization/project-name.git`  
2. 환경 변수 설정 (`.env` 파일 등)  
3. 실행  
   - Backend: `./gradlew bootRun`  
   - Frontend: `npm start`  

---



## 프론트엔드 프로젝트 구조
```plaintext
src
└── main
    └── resources
        ├── static
        │   ├── css
        │   │   ├── admin.css
        │   │   ├── admin_login.css
        │   │   └── order.css
        │   ├── img
        │   │   ├── cafe1.png
        │   │   ├── cafe2.png
        │   │   ├── cafe3.png
        │   │   └── cafe4.png
        │   └── js
        │       └── admin.js
        ├── templates
        │   ├── admin.html
        │   ├── admin_login.html
        │   ├── index.html
        │   ├── order-confirm.html
        │   ├── order-summary.html
        │   ├── order.html
        │   ├── user.html
        │   └── user_login.html
        └── application.yml
