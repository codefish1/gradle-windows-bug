import org.junit.jupiter.api.Test
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.io.PrintWriter

class ExampleTest {

    @TempDir
    lateinit var tempDir: File
    fun file(path: String): File = File(tempDir, path).also { assert(it.parentFile.mkdirs() || it.parentFile.exists())}

    @Test
    internal fun `when defining the environment map on Windows, SystemRoot is required`() {

        file("build.gradle.kts").writeText("""
            

            plugins {
                `kotlin-dsl`
            }
            
            repositories {
                mavenCentral()
            }
            
            dependencies {
            
                testImplementation(gradleTestKit())
                testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
                testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
            }
            
            tasks.withType<Test>().configureEach {
                useJUnitPlatform()
            }
            
            java {
                toolchain {
                    languageVersion.set(JavaLanguageVersion.of(17))
                }
            }
        """.trimIndent())


        GradleRunner.create()
                .withProjectDir(tempDir)
                .withEnvironment(mapOf())
                .forwardStdOutput(PrintWriter(System.out)).build()

    }

}