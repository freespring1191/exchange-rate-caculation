# 환율 계산 

## 사용 기술
- JAVA 11
- SpringBoot 2.1.2.RELEASE
- Lombok
- jQuery 3.3.1
- BootStrap 3.3.7
- Thymeleaf

## 환경 구성
1. JAVA 11 설치
2. maven 설치 

## 셋팅 방법
### application.properties 에 키 셋팅
> application.properties 에 API KEY 설정

```properties
apilayer.access-key=
```

## 실행방법
1. Maven Package 빌드
```bash
mvn clean package
``` 

2. `target` 디렉토리에 생성된 `exchange-rate-caculation-0.0.1-SNAPSHOT.jar` 파일을 `java -jar` 로 실행
```bash
java -jar target/exchange-rate-caculation-0.0.1-SNAPSHOT.jar
```