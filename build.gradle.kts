import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
    `maven-publish`
    signing
}

group = "com.scitotec.kotlinjpautils"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
    withJavadocJar()
}


publishing {
    publications {
        create<MavenPublication>("main") {
            from(components["java"])
            pom {

                name.set("Kotlin JPA Utils")
                packaging = "jar"
                description.set("A set of helpers for writing less boilerplate code when using Kotlin with JPA")
                url.set("https://github.com/scitotec/kotlin-jpa-utils")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("rworgul")
                        name.set("Robert Worgul")
                        email.set("robert.worgul@scitotec.de")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/scitotec/kotlin-jpa-utils.git")
                    developerConnection.set("scm:git:git@github.com:scitotec/kotlin-jpa-utils.git")
                    url.set("https://github.com/scitotec/kotlin-jpa-utils")
                }
            }
        }
    }
    repositories {
        maven {
            name = "ossrh"
            if (version.toString().endsWith("SNAPSHOT")) {
                url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            } else {
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            }
            credentials(PasswordCredentials::class)
        }
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["main"])
}

dependencies {
    compileOnly("jakarta.persistence:jakarta.persistence-api:2.2.1")
    val junitVersion = "5.8.0"
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("org.assertj:assertj-core:3.21.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}
