import org.gradle.kotlin.dsl.version

rootProject.name = "awp-enterprise-automation"

pluginManagement {
    plugins {
        val springBootVersion: String by settings
        val springDependencyVersion: String by settings
        val graalVMVersion: String by settings
        val sonarQubeVersion: String by settings

        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyVersion
        id("org.graalvm.buildtools.native") version graalVMVersion
        id("org.sonarqube") version sonarQubeVersion
    }
}

include("core")
include("service")
include("api")
