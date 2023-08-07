# spring-cms
spring 기반의 이커머스 cms입니다.

## 개요
간단한 커머스 프로젝트

Goal : 셀러와 구매자 사이를 중개해 주는 커머스 서버를 구축한다.

### 💡 Stack
![Static Badge](https://img.shields.io/badge/spring-passing?style=for-the-badge&logo=spring&logoColor=white&color=%236DB33F)
![Static Badge](https://img.shields.io/badge/jpa-passing?style=for-the-badge&logo=hibernate&logoColor=white&color=%2359666C)
![Static Badge](https://img.shields.io/badge/redis-passing?style=for-the-badge&logo=redis&logoColor=white&color=%23DC382D)
![Static Badge](https://img.shields.io/badge/docker-passing?style=for-the-badge&logo=docker&logoColor=white&color=%232496ED)
![Static Badge](https://img.shields.io/badge/aws-passing?style=for-the-badge&logo=amazon-aws&logoColor=white&color=%23232F3E)

## 회원
### 공통
- [ ] 이메일을 통해서 인증번호를 통한 회원가입

### 고객
- [ ] 회원 가입
- [ ] 인증 (이메일)
- [ ] 로그인 토큰 발행
- [ ] 로그인 토큰을 통한 제어 확인 (JWT, filter를 사용해서 간략하게)
- [ ] 예치금 관리

### 셀러
- [ ] 회원가입

## 주문 서버
### 판매자
- [ ] 상품 등록, 수정
- [ ] 상품 삭제

### 구매자
- [ ] 장바구니를 위한 Redis 연동
- [ ] 상품 검색 & 상세 페이지
- [ ] 장바구니에 물건 추가
- [ ] 장바구니 확인
- [ ] 주문하기
- [ ] 주문내역 이메일로 발송하기
