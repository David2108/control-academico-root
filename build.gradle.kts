import org.gradle.api.JavaVersion
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.*

val lombokVersion = "1.18.30"
val mapstructVersion = "1.5.5.Final"

val versionMajor = 1
val versionMinor = 0
val versionPatch = 0

// Lee número de build de Github Actions (o usar SNAPSHOT localmente)
val buildNumber = System.getenv("GITHUB_RUN_NUMBER")
// Detectar rama para decidir versión
val gitRef = System.getenv("GITHUB_REF") ?: "local"
val isRelease = gitRef.contains("refs/heads/master") || gitRef.contains("refs/heads/release/")

version = if (isRelease) {
    "$versionMajor.$versionMinor.$versionPatch"
}else {
    "$versionMajor.$versionMinor.$versionPatch-$buildNumber-SNAPSHOT"
}

println("Building version $version")

plugins {
    id("org.springframework.boot") version "3.5.0" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    kotlin("jvm") version "1.9.0" apply false
}

allprojects {
    group = "com.web.app"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    // Configurar para que todos los modulos puedan usar junit5
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {

    //Declara la version
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

        // Lombok
        "compileOnly"("org.projectlombok:lombok:$lombokVersion")
        "annotationProcessor"("org.projectlombok:lombok:$lombokVersion")

        // Mapper
        "implementation"("org.mapstruct:mapstruct:$mapstructVersion")
        "annotationProcessor"("org.mapstruct:mapstruct-processor:$mapstructVersion")

        // QueryDSL
        "implementation"("com.querydsl:querydsl-jpa:5.0.0:jakarta")
        "annotationProcessor"("com.querydsl:querydsl-apt:5.0.0:jakarta")
        "annotationProcessor"("jakarta.annotation:jakarta.annotation-api")
        "annotationProcessor"("jakarta.persistence:jakarta.persistence-api")
    }

    // Incluir carpeta de código generado para MapStruct y QueryDSL
    the<SourceSetContainer>().named("main") {
        java.srcDir("build/generated/sources/annotationProcessor/java/main")
    }
}

tasks.withType<Jar> {
    archiveBaseName.set("control-academico")
    archiveVersion.set("$version")
}

tasks.register("printVersion"){
    doLast {
        println(project.version)
    }
}
