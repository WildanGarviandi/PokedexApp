plugins {
    id("java-library")
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.converter.gson)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}