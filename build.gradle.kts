import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version PluginVersion.SPRING_BOOT_VERSION
	id("io.spring.dependency-management") version PluginVersion.DEPENDENCY_MANAGER_VERSION
	kotlin("jvm") version PluginVersion.JVM_VERSION
	kotlin("plugin.spring") version PluginVersion.SPRING_PLUGIN_VERSION
	kotlin("plugin.jpa") version PluginVersion.JPA_PLUGIN_VERSION
	kotlin("kapt") version PluginVersion.KAPT_VERSION
}

group = "team.msg"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven { url = uri("https://jitpack.io") }
}

dependencies {
	// spring starter
	implementation(Dependencies.SPRING_JPA)
	implementation(Dependencies.SPRING_SECURITY)
	implementation(Dependencies.SPRING_VALIDATION)
	implementation(Dependencies.SPRING_WEB)
	implementation(Dependencies.SPRING_REDIS)
	testImplementation(Dependencies.SPRING_STARTER_TEST)
	testImplementation(Dependencies.SPRING_SECURITY_TEST)

	// kapt
	kapt(Dependencies.CONFIG_PROCESSOR)

	// kotlin
	implementation(Dependencies.JACKSON_MODULE_KOTLIN)
	implementation(Dependencies.KOTLIN_REFLECT)

	// database
	runtimeOnly(Dependencies.MYSQL_DATABASE)

	// jwt
	implementation(Dependencies.JWT_API)
	runtimeOnly(Dependencies.JWT_IMPL)
	runtimeOnly(Dependencies.JWT_JACKSON)

	// gauth
	implementation(Dependencies.GAUTH)

	// mockito
	testImplementation(Dependencies.MOCKITO)

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
