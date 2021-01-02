plugins {
    //java
    kotlin("multiplatform") version "1.4.21"//"1.3.70"
    id ("com.github.hierynomus.license") version "0.15.0"
    `maven-publish`
    maven
    //id ("com.jfrog.bintray") version "1.8.0"
    id("org.jetbrains.dokka") version "0.9.17"
}


group = Config.Project.group
version = Config.Project.version

repositories {
    mavenLocal()
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://kotlin.bintray.com/kotlin-js-wrappers")
    maven("https://kotlin.bintray.com/kotlin-eap")
    maven("https://kotlin.bintray.com/kotlin-dev")
    mavenCentral()
    jcenter()
}

kotlin {
    js(){
        browser {
            dceTask {
                keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
            }
            testTask{
                useKarma{
                    useChromeHeadless()
                    useFirefox()
                }
            }
        }
        binaries.executable()
    }
    js().compilations["main"].defaultSourceSet  {
        dependencies {
            implementation(kotlin("reflect"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Config.Versions.coroutines}")
            implementation(kotlin("stdlib-js"))

            // evoleq
            implementation(Config.Dependencies.evoleqCoreJs) // need evolving type
            implementation(Config.Dependencies.dynamicsJs) // need ids
            implementation(Config.Dependencies.configurationsJs) // need configurations
            implementation("org.evoleq:mathcat-result-js:${Config.Versions.matcat}")
         
            
            //React, React DOM + Wrappers
            implementation("org.jetbrains:kotlin-react:17.0.0-pre.134-kotlin-1.4.21")
            implementation("org.jetbrains:kotlin-react-dom:17.0.0-pre.134-kotlin-1.4.21")
          //  implementation("org.jetbrains:kotlin-react-router-dom:5.1.2-pre.123-kotlin-1.4.10")
            implementation(npm("react", "^17.0.0"))
            implementation(npm("react-dom", "^17.0.0"))
         
        }
    }
    js().compilations["test"].defaultSourceSet {
        dependencies{
            implementation(kotlin("test-js"))
            //implementation(kotlin("test-annotations-js"))
        }
    /* ... */
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(kotlin("reflect"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Config.Versions.coroutines}")
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


apply(from = "./publish.mpp.gradle.kts")
