plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Runtime launcher for the tests
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Mockito
    testImplementation("org.mockito:mockito-core:5.8.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.8.0")
}

tasks.test {
    useJUnitPlatform()
}