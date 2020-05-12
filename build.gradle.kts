/*
plugins {
    // id("kotlin-1.3-compat")
    id("org.jetbrains.kotlin.js") version "1.3.70"//-eap-184"
    // id("org.jetbrains.kotlin.frontend")
    id ("com.github.hierynomus.license") version "0.15.0"
   // `publishing-conventions`
    `maven-publish`
    maven
    //kotlin("plugin.serialization") version "1.3.70"
}
*/
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
/*
dependencies {

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
    //implementation ("org.jetbrains:kotlin-react-redux:5.0.7-pre.70-kotlin-1.3.21")
    //implementation ("org.jetbrains:kotlin-redux:4.0.0-pre.70-kotlin-1.3.21")
    implementation(npm("react", "16.13.1"))
    implementation(npm("react-dom", "16.13.1"))
    implementation(npm("react-router-dom"))



    //implementation(npm("text-encoding", "0.7.0"))
    //implementation(npm("abort-controller", "3.0.0"))

}

*/
/*
kotlin{
    target{
        browser {
            dceTask {
                keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
            }
        }
    }
}
*/
kotlin {
    /* Targets configuration omitted.
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
    /*
    jvm().compilations["main"].defaultSourceSet {
        dependencies {
            implementation(kotlin("stdlib-jdk8"))
            implementation(kotlin("reflect"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
        }
    }

     */
    /*
    // JVM-specific tests and their dependencies:
    jvm().compilations["test"].defaultSourceSet {
        dependencies {
            implementation(kotlin("test-junit"))
        }
    }

     */

    js().compilations["main"].defaultSourceSet  {
        dependencies {
            //implementation(kotlin("js"))
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
            //implementation ("org.jetbrains:kotlin-react-redux:5.0.7-pre.70-kotlin-1.3.21")
            //implementation ("org.jetbrains:kotlin-redux:4.0.0-pre.70-kotlin-1.3.21")
            implementation(npm("react", "16.13.1"))
            implementation(npm("react-dom", "16.13.1"))
            implementation(npm("react-router-dom"))
        }
        /* ... */
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

