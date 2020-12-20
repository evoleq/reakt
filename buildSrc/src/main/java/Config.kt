object Config {

    object Project {
        const val group = "org.evoleq"
        const val version = "1.0.1"
        const val artifactId = "reakt"
    }

    object Versions {

        const val kotlin = "1.4.0"
        const val coroutines = "1.3.9"

        const val evoleqCore = "2.0.0-alpha"

        const val configurations = "2.0.0-alpha"
        const val dynamics = "2.0.0-alpha"

        const val matcat =  "1.0.1"
    }

    object Dependencies {
        const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        // evoleq
        const val evoleqCore = "org.evoleq:evoleq-core-jvm:${Versions.evoleqCore}"
        const val evoleqCoreJs = "org.evoleq:evoleq-core-js:${Versions.evoleqCore}"

        // configurations
        const val configurations = "org.evoleq:configurations-jvm:${Versions.configurations}"
        const val configurationsJs = "org.evoleq:configurations-js:${Versions.configurations}"

        // dynamics
        const val dynamics = "org.evoleq:dynamics-jvm:${Versions.dynamics}"
        const val dynamicsJs = "org.evoleq:dynamics-js:${Versions.dynamics}"
    }
}