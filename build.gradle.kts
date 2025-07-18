plugins {
    id("org.jetbrains.intellij") version "1.17.3"
    kotlin("jvm") version "1.9.23"
}

intellij {
    version.set("2023.3") // Set to your target IntelliJ version
    type.set("IC") // Community Edition
    plugins.set(listOf()) // Add required plugins here
}

group = "com.marcherdiego.json.visualizer"
version = "1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("lib/plantuml-1.2025.4.jar"))
    implementation(kotlin("stdlib"))
    // plantuml is not available on Maven Central, so keep as local jar if needed
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

sourceSets {
    main {
        java.srcDirs("src")
        resources.srcDirs("resources")
    }
}

tasks {
    patchPluginXml {
        version.set(project.version as String)
        sinceBuild.set("233") // Adjust for your IntelliJ version
        untilBuild.set("241.*")
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileJava {
        options.release.set(17)
    }
}