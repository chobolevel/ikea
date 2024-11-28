# IKEA Clone Project(2023.12.01~2024.01.31)

## 목차
> + [프로젝트 사용기술](#프로젝트-사용기술)
> + [배포 환경](#배포-환경)
> + [프로젝트를 진행한 이유](#프로젝트를-진행한-이유)
> + [DB 테이블 구조](#DB-테이블-구조)
> + [API DOCS](#API_DOCS)
> + [초기 화면](#초기-화면)
> + [주요 기능](#주요-기능)

## 프로젝트 사용기술

> <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=openjdk&logoColor=white">
> <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
> <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
> <img src="https://img.shields.io/badge/amazon rds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">
> <img src="https://img.shields.io/badge/redis-FF4438?style=for-the-badge&logo=redis&logoColor=white">
> <img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">
> <img src="https://img.shields.io/badge/ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white">
> <img src="https://img.shields.io/badge/aws ec2-ff9900?style=for-the-badge&logo=amazon ec2&logoColor=white">
> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
> <img src="https://img.shields.io/badge/thymeleaf-005f0f?style=for-the-badge&logo=thymeleaf&logoColor=white">
> <img src="https://img.shields.io/badge/jquery-0769ad?style=for-the-badge&logo=jquery&logoColor=white">

## 배포 환경
> ![image](https://github.com/chobolevel/ikea/assets/104749958/dc31569c-bcca-4797-9fc4-9e17bccec390)

## 프로젝트를 진행한 이유
> + 실제 사용하는 제품과 비슷한 수준의 개발을 혼자 처음부터 끝까지 해보고 싶었습니다. 
> + 또한 실전에서는 어떤 부분에 대해 고민하고 개발하는지 경험해 보고 싶었으며
> + 이전까지 배운 내용들을 활용해 보고 중간중간 새로운 부분도 익힐 수 있다고 생각되어 진행하였습니다.

## DB 테이블 구조
> ![image](https://github.com/chobolevel/ikea/assets/104749958/496c4b31-6e9a-4b5e-bb94-7142e0a39566)

## API_DOCS
> ![IKEA_API_DOCS](https://github.com/chobolevel/ikea/assets/104749958/95eb7efc-c2b5-4230-b955-5d235fc32230)

## 초기 화면
> ![localhost_8080_](https://github.com/chobolevel/ikea/assets/104749958/007ed815-9011-4822-932b-d5aa9c8f338b)


## 주요 기능
> 
> 1. 인증 <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/243731ef-5ff9-404a-9a0a-73a0ea3c0f17) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/a814c83c-5bc0-4955-88ff-d4a45ed20237) <br/>
>   비밀번호는 유출에 가장 민감한 정보이기 때문에 해당 필드는 쓰기 전용으로 관리, 또한 이중 안전을 위해 BCrypt 객체를 통한 암호화 활용하였습니다.

> 2. 인가 <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/f55addb5-1761-4006-afa0-f3306ca13d0e) <br/> 
>   사용자 권한별 허가된 페이지만 이동할 수 있도록 인가 처리를 통해 구분 없이 무분별한 이동을 방지하였습니다.

> 3. 아이디/비밀번호 찾기 기능 <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/a6a48af9-8376-46b9-9f1a-6edb316c623c) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/9df02c09-4a5a-461b-b90a-d66432200d30) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/47771269-9bd3-4124-949c-63d8264475f7) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/4e945926-76df-4f14-ad03-2bc9351a8592) <br/>
>    MailUtil의 코드 중 일부로 아이디/비밀번호 찾기 기능을 활용하는 경우 smtp 사용을 설정한 이메일을 환경 변수로 입력받아 메일 전송을 해당 이메일로 처리하도록 하였습니다. <br/> 인증번호의 경우 redis를 활용하여 3분동안 저장되도록 하여 인증하도록 처리하였습니다.

> 4. 상품/상품 상세 정보 등록 기능 <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/77d19051-1180-4897-bd11-ec8721da6278) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/3fda756a-759d-4efd-8f82-a2843e2552d7) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/3b1a0ec2-d54e-451a-80ca-61237f0c0ea5) <br/>
>    상품 선택 옵션을 생성하는 코드 중 파일을 저장하는 로직으로 먼저 상품 아이디로 구분하고 선택 옵션 아이디로 구분하여 각각 이미지를 저장하여 활용할 수 있도록 하였습니다.

> 5. 에러 처리 <br/> ![chobolevel site_404](https://github.com/chobolevel/ikea/assets/104749958/9cd4b8f8-2d55-413f-bf27-5285dedc7a66) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/4af8aab9-3841-44a7-85fe-2640428b2c45) <br/>
>     페이지에서 404등의 에러는 화면을 노출하여 해당 페이지를 사용할 수 없음을 사용자에게 알려주도록하였습니다.<br/> API의 경우 특정 파라미터 누락 등의 메시지를 통해 잘못된 부분을 쉽게 수정할 수 있게 하였습니다.
