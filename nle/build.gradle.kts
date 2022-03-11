plugins {
    kotlin("jvm") version "1.5.10"
    java
}

group = "ru.nanikon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    implementation("org.jetbrains.lets-plot:lets-plot-common:2.2.0")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:3.1.1")
    implementation("org.slf4j:slf4j-simple:1.7.32")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}