plugins {
    id("java")
    id("jacoco")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.2.0")
    }
}

dependencies {
    implementation(project(":shared")) // si usas shared

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Validations
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")

    // Database
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("com.ibm.db2:jcc:11.5.8.0")

    // Testing
    // Tes unit
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-junit-jupiter")
    // Test integration
    testImplementation(project(":app"))
    testImplementation("com.h2database:h2")

}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

// Configuración global de Jacoco para el módulo
jacoco {
    toolVersion = "0.8.11" // o la última versión estable
}

// Configura el reporte del task jacocoTestReport
tasks.jacocoTestReport {
    dependsOn(tasks.test) // Que siempre corra después de los tests
    reports {
        xml.required.set(true)  // ¡IMPORTANTE! Así Codecov/Sonar puede analizarlo
        html.required.set(false)
        csv.required.set(false)
    }
}