# springboot-mall-clonning
Spring Boot 쇼핑몰 클로닝 Step by Step


## 스텝 1
- 기본 설정 및 테스트

## Spring Boot + Spring Data JPA + QueryDSL
- Version에 맞춰서 설정하기 아주 힘듦
- Q클래스 생성 조건
    1. `@Entity`가 붙은 클래스 : Item 클래스에 @Entity가 있어야 함
    2. `annotationProcessor` 설정 : querydsl-apt + jpa classifier 필요
    3. Gradle 빌드 수행 : `./gradlew compileJava` 또는 build를 실행해야 함
    4. Java Compile Task 설정 : 생성된 Q타입을 srcDirs에 포함해야 IDE에서 인식

- plugin 설정

    ```gradle
    plugins {
        id 'java'
        id 'org.springframework.boot' version '3.2.5' // 현재 최신 안정 버전 기준
        id 'io.spring.dependency-management' version '1.1.4'
    }
    ```

- dependencies 설정

    ```gradle
    dependencies {
        // Spring Boot + JPA
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        runtimeOnly 'com.mysql:mysql-connector-j' // or H2

        // QueryDSL
        implementation 'com.querydsl:querydsl-jpa:5.0.0'
        annotationProcessor 'com.querydsl:querydsl-apt:5.0.0:jpa'
        annotationProcessor 'jakarta.annotation:jakarta.annotation-api'

        // Lombok
        compileOnly 'org.projectlombok:lombok'
        annotationProcessor 'org.projectlombok:lombok'

        // Test
        testImplementation 'org.springframework.boot:spring-boot-starter-test'
    }   
    ```

- Q타입 생성 디렉터리 설정

    ```gradle
    def generated = "$buildDir/generated/sources/annotationProcessor/java/main"

    sourceSets {
        main {
            java {
                srcDirs += [generated]
            }
        }
    }

    tasks.withType(JavaCompile).configureEach {
        options.annotationProcessorGeneratedSourcesDirectory = file(generated)
    }    
    ```


## Spring Boot Custom 에러페이지

```text
src/
└── main/
    └── resources/
        └── templates/
            └── error/
                ├── 404.html
                ├── 500.html
                └── error.html  ← 모든 예외의 기본값

```

- application.properties 설정

    ```properties
    server.error.whitelabel.enabled=false  # 기본 Whitelabel Error Page 비활성화
    server.error.include-message=always    # 오류 메시지 포함 (Thymeleaf에서 ${message} 사용 가능)
    server.error.include-binding-errors=always
    ```

## 지연로딩
| 구분       | 즉시 로딩 (EAGER)                    | 지연 로딩 (LAZY)                        |
| -------- | -------------------------------- | ----------------------------------- |
| 📦 로딩 시점 | 엔티티를 조회할 때 **즉시** 연관 엔티티까지 함께 로딩 | 연관 엔티티는 **실제로 사용할 때까지 로딩 지연**       |
| 🔄 동작 방식 | `JOIN` 또는 추가 쿼리로 함께 조회됨          | 프록시 객체로 대체되며, 접근 시 SQL 실행           |
| ⚡ 성능     | 불필요한 로딩으로 **성능 저하 가능**           | 필요한 경우에만 로딩되어 **성능 효율적**            |
| ⚠️ 주의사항  | N+1 문제 발생 가능, 순환 참조 위험           | `LazyInitializationException` 발생 가능 |
| 💬 대표 예시 | 회원 조회 시 주문 목록까지 자동으로 조회          | 회원만 먼저 조회 후, 주문은 나중에 접근 시 조회        |


### LazyInitializationException 예시
```java
@Transactional
public Member getMember(Long id) {
    return memberRepository.findById(id).get();
}

// 컨트롤러에서 member.getOrders() 호출 시 예외 발생
```

### 상품등록
1. bulid.gradle

    ```gradle
    // DTO ↔ Entity 간 변환(매핑) 도와주는 라이브러리
	implementation 'org.modelmapper:modelmapper:3.2.4'
    ```

2. com.hugo83.webmall.entity.ItemImg.java 생성
3. com.hugo83.webmall.dto.ItemImgDto.java 생성
4. com.hugo83.webmall.dto.ItemFormDto.java
5. com.hugo83.webmall.controller.ItemController.java
6. resources/templates/item/itemForm.html 작성

7. member 중 role 이 ADMIN으로 로그인
8. http://localhost:9092/admin/item/new 로 접근
 