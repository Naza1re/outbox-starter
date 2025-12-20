plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "1.9.25"
    kotlin("kapt") version "1.9.25"
    id("java-library")
    id("maven-publish")
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.kotlin"
version = "0.0.3"
description = "outbox-starter"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.jar {
    archiveBaseName.set("outbox-starter")
    archiveVersion.set(version.toString())
    archiveClassifier.set("")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.5.4"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.postgresql:postgresql")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    implementation("org.mapstruct:mapstruct:1.6.3")
    kapt("org.mapstruct:mapstruct-processor:1.6.3")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
            groupId = "com.kotlin"
            artifactId = "outbox-starter"
            version = project.version.toString()
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Naza1re/outbox-starter")
            credentials {
                username = project.findProperty("gpr.user") as? String ?: System.getenv("USERNAME_GITHUB")
                password = project.findProperty("gpr.key") as? String ?: System.getenv("TOKEN_GITHUB")
            }
        }
    }
}
