plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.test"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
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

//	implementation("org.springframework.boot:spring-boot-starter")
//	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("org.apache.logging.log4j:log4j-api:2.23.1")
	implementation("org.apache.logging.log4j:log4j-core:2.23.1")
//	implementation("org.slf4j:slf4j-simple:2.0.13")


	implementation("org.apache.kafka:kafka-clients:3.1.0")
	implementation("org.slf4j:slf4j-api:2.0.13")
	implementation("org.slf4j:slf4j-log4j12:2.0.13")
	implementation("com.googlecode.json-simple:json-simple:1.1.1")

	implementation("redis.clients:jedis:5.1.2")

}

tasks.withType<Test> {
	useTestNG()
}
