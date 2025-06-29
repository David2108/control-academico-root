plugins {
    id("org.springframework.boot")
}
dependencies {
    implementation(project(":academic-core"))
    implementation(project(":attendance"))
    implementation(project(":student"))
    implementation(project(":enrollment"))
    implementation(project(":personnel"))
    implementation(project(":shared"))

    implementation("org.springframework.boot:spring-boot-starter-web")

    // Database
    runtimeOnly("mysql:mysql-connector-java:8.0.33")
    runtimeOnly("org.postgresql:postgresql:42.7.3")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.ibm.db2:jcc:11.5.8.0")

    // Liquibase
    implementation("org.liquibase:liquibase-core")

    // Actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")

}