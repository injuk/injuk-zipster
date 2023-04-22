import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"

    id("org.openapi.generator") version "6.4.0"
}

group = "com.injuk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

val kotestVersion = "5.5.5"
val mockkVersion = "1.13.5"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    // kotest
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")

    // mockk
    testImplementation("io.mockk:mockk:$mockkVersion")

    // jakarta
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

openApiGenerate {
    generatorName.set("kotlin-spring")

    configOptions.set(
        mapOf(
            "reactive" to "false",
            "interfaceOnly" to "true",
            "enumPropertyNaming" to "UPPERCASE",
            "useBeanValidation" to "true",
            "useSpringBoot3" to "true",
            "oas3" to "false",
            "documentationProvider" to "none",
            "annotationLibrary" to "none",
        )
    )

    inputSpec.set("$rootDir/src/main/resources/api.yml")
    outputDir.set("$buildDir/generated/openapi/")

    apiPackage.set("com.injuk.zipster.operations")
    modelPackage.set("com.injuk.zipster.models")

    generateApiDocumentation.set(false)
    generateModelDocumentation.set(false)
}

kotlin {
    sourceSets["main"].apply {
        kotlin.srcDirs(
            listOf(
                "$buildDir/generated/openapi/src/main",
            )
        )
    }
}