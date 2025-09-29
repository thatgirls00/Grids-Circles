# ☕️ Grids & Circles

> Spring으로 CRUD를 구현하며 백엔드 개발의 핵심을 경험하고, 실무에 가까운 협업 방식을 익히는 프로젝트

---

## Description
이 프로젝트는 작은 로컬 카페 **'Grids & Circles'** 의 온라인 주문 시스템을 구현하는 것을 목표로 합니다.  
고객은 웹사이트를 통해 원두 패키지를 주문하며, 시스템은 매일 전날 오후 2시부터 당일 오후 2시까지의 주문을 모아 처리합니다.  
이를 위해 메뉴 데이터를 관리할 수 있는 **CRUD(Create, Read, Update, Delete)** 기능을 Spring 기반으로 구현합니다.  

---

## Roles in Charge

<div sytle="overflow:hidden;">
<table>
  <tr>
    <td colspan="1" align="center"><strong>FrontEnd & BackEnd</strong></td>
    <td colspan="2" align="center"><strong>BacKEnd</strong></td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/thatgirls00"><img src="https://avatars.githubusercontent.com/u/109068985?v=4" width="150px;" alt="전현수"/><br/><sub><b>전현수</b></sub></a>
    </td>
    <td align="center">
      <a href="https://github.com/whyin"><img src="https://avatars.githubusercontent.com/u/67681246?v=4" width="150px" alt="김영인"/><br/><sub><b>김영인</b></sub></a>
    </td>
    <td align="center">
      <a href="https://github.com/LimByeongSu"><img src="https://avatars.githubusercontent.com/u/184930643?v=4" width="150px" alt="임병수"/><br/><sub><b>임병수</b></sub></a>
    </td>
  </tr>
</table>

> 전현수 : 팀장, Frontend 담당 전체 프로젝트 기획 및 일정 관리 / 프론트엔드 개발 (UI 설계, 정적 리소스 구성) / DB 테이블 설계 및 관리 / 커피 이미지 연동 및 백엔드 코드 수정 <br><br>
> 김영인 : 팀원, Backend 상품 등록 및 조회 기능 구현 / 관리자 페이지 관련 로직 개발 <br><br>
> 임병수 : 팀원, Backend 주문 처리 관련 API 개발 / 사용자 정보 처리 로직 구현 / JPA 기반 도메인 설계 및 예외 처리 <br><br>
</div>
<br>

---

## Main Function 
- 메뉴 데이터 관리 (생성, 조회, 수정, 삭제)  
- 주문 내역 저장 및 고객 식별 (이메일 기반)  
- 동일 고객의 하루 주문 합산 처리  
- 배치 로직: 오후 2시 이후 주문은 다음 날 배송 시작  

---

## Tech Stack
<div>
    <table>
        <tr>
            <td colspan="2" align="center">
               FrontEnd
            </td>
            <td colspan="4">
                    <img src = "https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white">
                    <img src = "https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E">
                    <img src = "https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Framework
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Database
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
                <img src="https://img.shields.io/badge/H2-000000?style=for-the-badge&logo=H2&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Tool
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                etc.
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=GitHub&logoColor=white"/>
                <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white">
                <img src="https://img.shields.io/badge/Slack-000000?style=for-the-badge&logo=slack&logoColor=white">
                <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white">
            </td>
        </tr>
    </table>
</div>

---


## FrontEnd Project Structure
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
