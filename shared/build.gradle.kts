plugins {
    id("java")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.2.0")
    }
}

dependencies {
    // AuditorAware
    implementation("org.springframework.data:spring-data-commons")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework:spring-web")
    implementation("org.springframework:spring-context")

    // Validación (solo API)
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")

    // JPA (solo anotaciones básicas)
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    // Utilidades
    implementation("org.apache.commons:commons-lang3:3.14.0")

}
