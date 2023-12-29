# IKEA Clone Project(단독 프로젝트) - 개발진행중

## 프로젝트 서비스 링크
> [IKEA-CLONE](https://www.ikea-clone.shop/)
> + AWS EC2 인스턴스를 이용하여 프로젝트를 배포하였습니다.
> + DB는 AWS의 RDS 서비스를 이용해서 사용할 수 있도록 하였습니다.
> + 테스트용 사용자 계정(아이디 비밀번호): user 1234
> + 테스트용 관리자 계정(아이디 비밀번호): admin 1234

## 프로젝트를 진행한 이유
>1. 실제 프로덕트와 비슷한 수준의 개발을 해보고 싶었습니다.
>2. 혼자 처음부터 개발해 보며 모든 부분에 걸쳐서 경혐해 보고 싶었습니다.
>3. 실전 개발에서는 어떤 부분을 조심해야 하는지 고민해 보고 싶었습니다.
>4. 이전까지 배우고 익혔던 기술들을 모두 활용해 보고 싶었습니다.
>5. 프로젝트를 하며 새로운 부분도 익힐 수 있다고 생각되었습니다.

## 프로젝트 사용기술
>+ Java 17
>+ SpringBoot 2.7.X + SpringSecurity
>+ spring-boot-starter-mail
>+ MySQL 8.0
>+ MyBatis 2.3.1
>+ Thymeleaf
>+ JQuery 3.7.1
>+ JQuery-validation
>+ AWS EC2, RDS

## DB 테이블 구조
> ![image](https://github.com/chobolevel/ikea/assets/104749958/496c4b31-6e9a-4b5e-bb94-7142e0a39566)

## API_DOCS
> ![IKEA_API_DOCS](https://github.com/chobolevel/ikea/assets/104749958/95eb7efc-c2b5-4230-b955-5d235fc32230)

## 초기 화면
> ![localhost_8080_](https://github.com/chobolevel/ikea/assets/104749958/f2c12eaa-43d5-497b-ab96-c120a3217b80)


## 주요 기능
> 
> 1. 인증 <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/243731ef-5ff9-404a-9a0a-73a0ea3c0f17) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/a814c83c-5bc0-4955-88ff-d4a45ed20237) <br/>
>   비밀번호는 유출에 가장 민감한 정보이기 때문에 해당 필드는 쓰기 전용으로 관리, 또한 이중 안전을 위해 BCrypt 객체를 통한 암호화 활용하였습니다.

> 2. 인가 <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/f55addb5-1761-4006-afa0-f3306ca13d0e) <br/> 
>   사용자 권한별 허가된 페이지만 이동할 수 있도록 인가 처리를 통해 구분 없이 무분별한 이동을 방지하였습니다.

> 3. 아이디/비밀번호 찾기 기능 <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/51f0761d-3234-4c1c-a4ed-1ec27031c818) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/c11718d0-30c3-4def-b7aa-a9636dd56936) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/47771269-9bd3-4124-949c-63d8264475f7) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/4e945926-76df-4f14-ad03-2bc9351a8592) <br/>
>    MailUtil의 코드 중 일부로 아이디/비밀번호 찾기 기능을 활용하는 경우 smtp 사용을 설정한 이메일을 환경 변수로 입력받아 메일 전송을 해당 이메일로 처리하도록 하였습니다.

> 4. 상품/상품 상세 정보 등록 기능 <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/77d19051-1180-4897-bd11-ec8721da6278) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/3fda756a-759d-4efd-8f82-a2843e2552d7) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/3b1a0ec2-d54e-451a-80ca-61237f0c0ea5) <br/>
>    상품 선택 옵션을 생성하는 코드 중 파일을 저장하는 로직으로 먼저 상품 아이디로 구분하고 선택 옵션 아이디로 구분하여 이미지를 저장하여 활용할 수 있도록 하였습니다.

> 5. 에러 처리 <br/> ![localhost_8080_product_list_delete](https://github.com/chobolevel/ikea/assets/104749958/91a48120-e91a-4151-97f3-092567d9184c) <br/> ![image](https://github.com/chobolevel/ikea/assets/104749958/4af8aab9-3841-44a7-85fe-2640428b2c45) <br/>
>     페이지에서 404등의 에러는 화면을 노출하여 해당 페이지를 사용할 수 없음을 사용자에게 알려주었으며 API의 경우 특정 파라미터 누락 등의 메시지를 통해 잘못된 부분을 쉽게 수정할 수 있게 하였습니다.