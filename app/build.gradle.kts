plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("java")
}
dependencies {

    implementation(project(":academic-core"))
    implementation(project(":attendance"))
    implementation(project(":student"))
    implementation(project(":enrollment"))
    implementation(project(":personnel"))
    implementation(project(":shared"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

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

// Indica cual es la clase main, para que bootJar la ejecute
springBoot {
    mainClass = "com.web.app.controlacademico.app.ControlAcademicoApplication"
}

val integrationTestSourceSet = sourceSets.create("integrationTest") {
    java.srcDir("src/integrationTest/java")
    resources.srcDir("src/integrationTest/resources")
    compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
    runtimeClasspath += output + compileClasspath
}

configurations["integrationTestImplementation"].extendsFrom(configurations["testImplementation"])
configurations["integrationTestRuntimeOnly"].extendsFrom(configurations["testRuntimeOnly"])

tasks.register<Test>("integrationTest") {
    description = "Ejecuta pruebas de integración"
    group = "verification"
    testClassesDirs = integrationTestSourceSet.output.classesDirs
    classpath = integrationTestSourceSet.runtimeClasspath
    shouldRunAfter(tasks.test)
    useJUnitPlatform()
}

tasks.named("check") {
    dependsOn("integrationTest")
}