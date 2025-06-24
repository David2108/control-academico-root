import org.gradle.api.JavaVersion
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.*

val mapstructVersion = "1.5.5.Final"
val querydslVersion = "5.0.0"
val lombokVersion = "1.18.30"

plugins {
    id("io.spring.dependency-management") version "1.1.7" apply false
    id("org.springframework.boot") version "3.5.0" apply false
    kotlin("jvm") version "1.9.0" apply false
}

allprojects {
    group = "com.web.app"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    // Repositorio adicional para IBM DB2
    repositories {
        mavenCentral()
        maven {
            url = uri("https://public.dhe.ibm.com/ibmdl/export/pub/software/websphere/maven/repo/")
        }
    }

    // Configurar compatibilidad Java
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    // Configurar dependencias comunes
    configurations.configureEach {
        resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    }

    dependencies {

        //Lombok
        "compileOnly"("org.projectlombok:lombok:$lombokVersion")
        "annotationProcessor"("org.projectlombok:lombok:$lombokVersion")

        "testImplementation"("org.springframework.boot:spring-boot-starter-test")
        "annotationProcessor"("org.springframework.boot:spring-boot-configuration-processor")
    }

    // Configurar tests
    tasks.withType<Test> {
        useJUnitPlatform()
    }

    // Incluir carpeta de código generado para MapStruct y QueryDSL
    the<SourceSetContainer>().named("main") {
        java.srcDir("build/generated/sources/annotationProcessor/java/main")
    }
}
