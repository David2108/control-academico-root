plugins {
    id("org.springframework.boot")
    id("java")
    id("jacoco")
}

dependencies {
    implementation(project(":shared")) // si usas shared

    implementation("org.springframework.boot:spring-boot-starter-web")

    // Validations
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")

    // Database
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("com.ibm.db2:jcc:11.5.8.0")

    // QueryDSL
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

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