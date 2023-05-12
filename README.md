
### 👪 팀 구성(총 5명)

- 백엔드 1명, 프론트2명, ROS 제어 2명

### ❓ 개발 동기

- 위드 코로나 시대 비대면 문화를 거치면서 달라진 새로운 라이프 스타일의 등장으로 소비자들은 비대면 서비스를 더 원하는 추세로 바뀌었습니다.  무인 가게와 비대면 주문,결제 시스템을 도입한 가게들이 많아 지고 있으며 구인비 절감과 소비자와 업주간의 의사소통의 문제를 해결 할 수 있는 장점들을 가지고 있기 때문에 서빙로봇 관제 및 주문 웹 사이트 제작하게 되었습니다.

### 🌐기획 의도

- 간편한 UI, 실시간 카메라 연동으로 사용자와 관리자에게 편의성을 제공하고 다양한 업종의 무인가게로 확장 가능한 구조를 생각하며 개발하였습니다.


### 👤 사용 대상

- 로봇으로 주문 테이블로 서빙 서비스를 사용하실 가게의 사장님

### ☀️ UI 디자인 설계

- 카카오 오븐 앱을 이용하여 자세한 UI/UX를 구성하고 프로젝트의 방향성을 확실하게 하기위해 사용하였습니다.

### 💻 담당 업무

- 서비스 기획 및 PM 역할 수행
- 백엔드 개발
    - Spring boot 프레임워크를 사용하여 REST API 방식으로 리액트와 데이터를 주고받고, 관리자와 사용자 기능을 분리하여 로봇 주문 사이트를 구현하였습니다.
    - 관리자 페이지에서는 손님의 실시간 주문 내용과 로봇의 상태정보, 시뮬레이션 화면에서 로봇의 배달 여부를 확인할 수 있습니다.
    - 그리고 사용자 페이지에서는 손님들이 상품을 주문했을 때 서버를 통하여 데이터들을 저장하고 서버에서는 관리자 페이지에 해당 데이터를 보내도록 구성하였습니다.
    - 데이터 설계 사이트에서 제공하는 관계도를 토대로 데이터 맵핑 도구를 사용하여 데이터베이스와 연동하여 관리하였습니다.
    - 또한 로그인 처리와 사용자의 보안을 담당하는 Spring 프레임워크의 Security를 이용하였습니다.
- 프론트 개발
    - axios를 이용한 서버 통신 및 데이터 후 처리

### ❓ 제공 서비스
- 손님이 주문 테이블에서 주문시 모니터링 페이지 주문 접수 내역 도착
- 서빙 할 해당 테이블을 선택 후 로봇이 출발
- 맵 학습 기반의 자율 주행, 주행 시뮬레이션, 이미지 분석 및 텍스트 검출

## 🛠️ 사용 기술 및 라이브러리

- Spring boot 웹 프레임워크
- MariaDB 데이터베이스 시스템
- 자바
- 파이썬
- 리액트
- JPA
- JWT
- SLAM & navigation
- GAZEBO 시뮬레이터
- OpenCV

## 🖥 담당 업무
- 서비스 기획 및 PM 역할 수행
- 백엔드 개발
- Spring boot 프레임워크를 사용하여 REST API 방식으로 리액트와 데이터를 주고받고, 관리자와 사용자 기능을 분리하여 로봇 주문 사이트를 구현하였습니다.
- 관리자 페이지에서는 손님의 실시간 주문 내용과 로봇의 상태정보, 시뮬레이션 화면에서 로봇의 배달 여부를 확인할 수 있습니다.
- 그리고 사용자 페이지에서는 손님들이 상품을 주문했을 때 서버를 통하여 데이터들을 저장하고 서버에서는 관리자 페이지에 해당 데이터를 보내도록 구성하였습니다.
- 데이터 설계 사이트에서 제공하는 관계도를 토대로 데이터 맵핑 도구를 사용하여 데이터베이스와 연동하여 관리하였습니다.
- 또한 로그인 처리와 사용자의 보안을 담당하는 Spring 프레임워크의 Security를 이용하였습니다.

- 프론트앤드 개발
- Admin page의 데이터 처리 구현
- 카카오+회원가입 page 구현
### 🗓️ 프로젝트 진행 기간

- **2023.2.10~2023.3.29**
