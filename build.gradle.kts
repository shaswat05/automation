plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("io.qameta.allure") version "2.11.2"
}

group = "com.test"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    all {
        exclude("ch.qos.logback", "logback-classic")
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.testng:testng:7.10.2")
    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.20.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.4")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {}
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation("org.apache.logging.log4j:log4j-api:2.23.1")
    implementation("org.apache.logging.log4j:log4j-to-slf4j:2.23.1")

//    implementation("org.apache.logging.log4j:log4j-core:2.23.1")
//	implementation("org.slf4j:slf4j-simple:2.0.13")


    implementation("org.apache.kafka:kafka-clients:3.1.0")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("org.slf4j:slf4j-log4j12:2.0.13")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")

    implementation("redis.clients:jedis:5.1.2")
    implementation("mysql:mysql-connector-java:8.0.33")

    implementation("io.qameta.allure:allure-testng:2.27.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.20.0")

    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("com.sun.xml.bind:jaxb-impl:4.0.5")


}

sourceSets {
    main {
        java {
            srcDirs("src/main/java", "src/test/java")
        }
    }
}

tasks.withType<Test> {
    useTestNG()
}

allure {
    adapter {
        frameworks {
            junit4.autoconfigureListeners.set(false)
            junit5.autoconfigureListeners.set(false)
            testng.autoconfigureListeners.set(true)
        }
    }
}
