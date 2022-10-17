//import org.jetbrains.kotlin.gradle.internal.kapt.incremental.UnknownSnapshot.classpath
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.openjfx.javafxplugin") version "0.0.13"
}

javafx {
    version = "11.0.2"
    modules("javafx.controls", "javafx.fxml" , "javafx.graphics")
}

group = "org.setu.crashReport"
version = "1.0-SNAPSHOT"



repositories {
    mavenCentral()
    mavenCentral()
    maven {
        setUrl("https://plugins.gradle.org/m2/")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation ("io.github.microutils:kotlin-logging:2.1.23")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("no.tornado:tornadofx:1.7.20")
  //  implementation( "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
  //  implementation( "org.junit.platform:junit-platform-gradle-plugin:1.1.0")
// Add JavaFX plugin:
   // implementation("org.openjfx:javafx-plugin:0.0.7")
    implementation(kotlin("stdlib-jdk8"))
}


tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}