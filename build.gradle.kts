plugins {
    //java
    kotlin("multiplatform") version "1.3.70"
    id ("com.github.hierynomus.license") version "0.15.0"
    `maven-publish`
    maven
    id ("com.jfrog.bintray") version "1.8.0"
    id("org.jetbrains.dokka") version "0.9.17"
}


group = Config.Project.group
version = Config.Project.version

repositories {
    mavenLocal()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
    mavenCentral()
    jcenter()
}

kotlin {
    js(){
        browser {
            dceTask {
                keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
            }
        }
        
    }
    js().compilations["main"].defaultSourceSet  {
        dependencies {
            implementation(kotlin("reflect"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.5")
            implementation(kotlin("stdlib-js"))

            // evoleq
            implementation(Config.Dependencies.evoleqCoreJs)
            implementation(Config.Dependencies.dynamicsJs)
            implementation(Config.Dependencies.configurationsJs)
            implementation("org.evoleq:mathcat-result-js:1.0.0")

            //React, React DOM + Wrappers
            implementation("org.jetbrains:kotlin-react:16.13.0-pre.94-kotlin-1.3.70")
            implementation("org.jetbrains:kotlin-react-dom:16.13.0-pre.94-kotlin-1.3.70")
            implementation("org.jetbrains:kotlin-react-router-dom:4.3.1-pre.70-kotlin-1.3.21")
            implementation(npm("react", "16.13.1"))
            implementation(npm("react-dom", "16.13.1"))
            implementation(npm("react-router-dom"))
        }
    }
    js().compilations["test"].defaultSourceSet {/* ... */ }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(kotlin("reflect"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}


tasks{
    val licenseFormatJsMain by creating(com.hierynomus.gradle.license.tasks.LicenseFormat::class) {
        source = fileTree("$projectDir/src/jsMain/kotlin") {
        }
        group = "license"
    }
    val licenseFormatCommonMain by creating(com.hierynomus.gradle.license.tasks.LicenseFormat::class) {
        source = fileTree("$projectDir/src/commonMain/kotlin") {
        }
        group = "license"
    }
    licenseFormat {
        finalizedBy(licenseFormatJsMain, licenseFormatCommonMain)
    }
}


//apply(from = "gradle-files/maven.publish.gradle.kts")

/*
bintray{
    val bintrayUser: String by project
    val bintrayKey: String by project
    publish = true
    setPublications("js", "kotlinMultiplatform", "metadata")
    pkg(delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
        repo = ""
        name = "x"
        userOrg = "evoleq"
        //websiteUrl = "https://blog.simon-wirtz.de"
        githubRepo = "evoleq/reakt"
        vcsUrl = "https://github.com/evoleq/reakt"
        //description = "Simple Lib for TLS/SSL socket handling written in Kotlin"
        setLabels("kotlin")
        setLicenses("Apache 2.0")
        //desc = description
    })
}


 */