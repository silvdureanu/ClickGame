plugins {
    id("java")
}

group = "uk.ungureanu.clickgame"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("redis.clients:jedis:5.0.2")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.json:json:20231013")

}

tasks.test {
    useJUnitPlatform()
}