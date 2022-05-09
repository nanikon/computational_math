plugins {
    kotlin("jvm") version "1.5.10"
    id("java")
}

group = "ru.nanikon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation( "org.jetbrains.kotlin:kotlin-stdlib")
}
