@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.versionUpdate)
    alias(libs.plugins.catalogUpdate)
    alias(libs.plugins.pitest)
    jacoco
    application
}

group = "com.poisonedyouth"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.poisonedyouth.rps.MainKt")
}

dependencies {
    // logback
    implementation(libs.logback)

    // arrow
    implementation(platform(libs.arrowStack))
    implementation(libs.arrowCore)

    // testing
    testImplementation(libs.junit5Engine)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.kotestArrowAssert)
    testImplementation(libs.kotestCoreAssertion)
    testImplementation(libs.kotestRunnerJunit5)
    testImplementation(libs.archUnitJunit5)
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

val jdkVersion: String = libs.versions.jdkVersion.get()
kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(jdkVersion))
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

pitest {
    setProperty("junit5PluginVersion", libs.pitestJunit5.get().version)
    setProperty("testPlugin", "junit5")
    setProperty("targetClasses", listOf("com.poisonedyouth.rps.*"))
    setProperty("mutationThreshold", 10)
    setProperty("outputFormats", listOf("HTML"))
    setProperty("threads", 8)
    setProperty("withHistory", true)
    setProperty("failWhenNoMutations", false)
    setProperty("useClasspathFile", true)
    setProperty("jvmArgs", listOf("-Xmx16G"))
    setProperty("avoidCallsTo", listOf("kotlin.jvm.internal"))
}
