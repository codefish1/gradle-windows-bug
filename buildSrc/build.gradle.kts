import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {

    testImplementation(gradleTestKit())
    testImplementation("org.junit.juipter:junit-juipter-api:5.8.2")
    testRuntimeOnly("org.junit.juipter:junit-juipter-engine:5.8.2")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

