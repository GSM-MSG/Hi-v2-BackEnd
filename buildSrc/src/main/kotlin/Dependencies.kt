object Dependencies {

    // kotlin
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"

    // spring
    const val SPRING_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val SPRING_REDIS = "org.springframework.boot:spring-boot-starter-data-redis"
    const val SPRING_SECURITY = "org.springframework.boot:spring-boot-starter-security"
    const val SPRING_WEB = "org.springframework.boot:spring-boot-starter-web"
    const val SPRING_VALIDATION = "org.springframework.boot:spring-boot-starter-validation"
    const val CONFIG_PROCESSOR = "org.springframework.boot:spring-boot-configuration-processor"
    const val SPRING_STARTER_TEST = "org.springframework.boot:spring-boot-starter-test"
    const val SPRING_SECURITY_TEST = "org.springframework.security:spring-security-test"
    const val SPRING_AOP = "org.springframework.boot:spring-boot-starter-aop"

    // mockito
    const val MOCKITO = "org.mockito.kotlin:mockito-kotlin:${DependencyVersions.MOCKITO_VERSION}"

    // jackson
    const val JACKSON_MODULE_KOTLIN = "com.fasterxml.jackson.module:jackson-module-kotlin"

    // database
    const val MYSQL_DATABASE = "mysql:mysql-connector-java"
    const val MARIADB_DATABASE = "org.mariadb.jdbc:mariadb-java-client"

    // jwt
    const val JWT_API = "io.jsonwebtoken:jjwt-api:${DependencyVersions.JWT_API_VERSION}"
    const val JWT_IMPL = "io.jsonwebtoken:jjwt-impl:${DependencyVersions.JWT_IMPL_VERSION}"
    const val JWT_JACKSON= "io.jsonwebtoken:jjwt-jackson:${DependencyVersions.JWT_JACKSON_VERSION}"

    // gauth
    const val GAUTH = "com.github.GSM-MSG:GAuth-SDK-Java:${DependencyVersions.GAUTH_VERSION}"

    // cloudwatch appender
    const val CLOUDWATCH_APPENDER = "ca.pjer:logback-awslogs-appender:${DependencyVersions.CLOUDWATCH_APPENDER_VERSION}"

}