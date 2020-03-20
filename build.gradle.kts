import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.micronaut.example"
version = "1.0-SNAPSHOT"

plugins {
    val kotlin_version = "1.3.61"

    base apply true
    kotlin("jvm") version kotlin_version apply true
    kotlin("plugin.allopen") version kotlin_version apply true
    kotlin("kapt") version kotlin_version apply true
    application apply true
}

dependencies {
    val kotlinx_version = "1.3.4"
    val micronaut_version = "1.3.3"
    val micronaut_hibernate_version = "1.2.0"
    val logging = "ch.qos.logback:logback-classic:1.2.3"

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_version")

    implementation(platform("io.micronaut:micronaut-bom:$micronaut_version"))
    implementation("io.micronaut:micronaut-runtime:$micronaut_version")
    implementation("io.micronaut:micronaut-http-server-netty:$micronaut_version")
    implementation("io.micronaut:micronaut-http-client:$micronaut_version")

    implementation("io.micronaut.configuration:micronaut-hibernate-validator:$micronaut_hibernate_version")

    kapt(platform("io.micronaut:micronaut-bom:$micronaut_version"))
    kapt("io.micronaut:micronaut-inject-java:$micronaut_version")
    kapt("io.micronaut:micronaut-validation:$micronaut_version")
    kapt("io.micronaut.configuration:micronaut-openapi:$micronaut_version")

    implementation(logging)
}

repositories {
    jcenter()
    mavenCentral()
}

application {
    mainClassName = "ApplicationEntryPoint"
}

allOpen {
    annotations("io.micronaut.aop.Around", "io.micronaut.http.annotation.Controller")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        javaParameters = true
        freeCompilerArgs = listOf("-XXLanguage:+NewInference")
    }
}