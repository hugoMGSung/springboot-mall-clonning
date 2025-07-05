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