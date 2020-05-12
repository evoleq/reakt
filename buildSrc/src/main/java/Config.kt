object Config {

    object Project {
        val group = "org.evoleq"
        val version = "1.0.0"
        val artifactId = "reakt"
    }

    object Versions {

        val kotlin = "1.3.70"
        val coroutines = "1.3.5"
        val kotlinReflect = "1.3.70"
        val kotlinSerialization = "0.20.0"//""0.14.0"


        val evoleq = "1.1.1"
        val evoleqCore = "0.0.0"

        val configurations = "0.0.0"
        val dynamics = "0.0.0"

        val evoleqfx = "1.3.2"
        val algebraicTypes = "1.0.23"

        val jfxmobile = "1.3.11"
        val retrolambda = "3.7.0" //"+"
        val javafxports = "8.60.9"

        val grpc = "1.15.1"//"1.27.2"//
        val protobuf = "3.6.1"

        val androidPlugin = "3.2.1"

        val fontawesomefx = "8.9"

        val junit = "4.12"

        const val kotlinGradlePlugin = "1.3.61"
        const val shadow = "5.2.0"
        const val ktor = "1.3.2"//"1.2.6"
        const val logback = "1.2.3"
        const val slf4j = "1.7.25"


    }

    object Dependencies {
        val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinReflect}"
        val kotlinSerializationRuntime =
            "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinSerialization}"//${Versions.kotlin}"
        val kotlinSerializationRuntimeCommon =
            "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.kotlinSerialization}"//
        val kotlinSerializationRuntimeNative =
            "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.kotlinSerialization}"//

        // evoleq
        val evoleq = "org.drx:evoleq:${Versions.evoleq}"
        val evoleqCore = "org.drx:evoleq-core-jvm:${Versions.evoleqCore}"
        val evoleqCoreJs = "org.drx:evoleq-core-js:${Versions.evoleqCore}"

        // configurations
        val configurations = "org.drx:configurations-jvm:${Versions.configurations}"
        val configurationsJs = "org.drx:configurations-js:${Versions.configurations}"

        // dynamics
        val dynamics = "org.drx:dynamics-jvm:${Versions.dynamics}"
        val dynamicsJs = "org.drx:dynamics-js:${Versions.dynamics}"

        val evoleqfx = "org.drx:evoleq-fx:${Versions.evoleqfx}"
        val algebraicTypes = "org.drx:kotlin-algebraic-types-plugin:${Versions.algebraicTypes}"

        val jfxmobile = "org.javafxports:jfxmobile-plugin:${Versions.jfxmobile}"
        val retrolambda = "gradle.plugin.me.tatarka:gradle-retrolambda:${Versions.retrolambda}"
        val orfjacklRetrolambdaConfig = "net.orfjackal.retrolambda:retrolambda:+"

        val androidToolsGradle = "com.android.tools.build:gradle:${Versions.androidPlugin}"
        val fonawesomefx = "de.jensd:fontawesomefx:${Versions.fontawesomefx}"

        val junit = "junit:junit:${Versions.junit}"
        val grpcTesting = "io.grpc:grpc-testing:${Config.Versions.grpc}"
        val testevoleqfx = "org.drx:test-evoleq-fx:1.0.1"

        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}"
        const val shadow = "com.github.jengelman.gradle.plugins:shadow:${Versions.shadow}"
        const val ktorServerNetty = "io.ktor:ktor-server-netty:${Versions.ktor}"
        const val ktorHtmlBuilder = "io.ktor:ktor-html-builder:${Versions.ktor}"
        const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"


    }
}