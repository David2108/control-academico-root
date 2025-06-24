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
}