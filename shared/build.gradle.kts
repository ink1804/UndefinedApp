plugins {
    id("kmm-base")
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":shared-common-network"))
            }
        }

        val iosMain by getting {
            dependsOn(commonMain)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            export(project(":shared-common-network"))
            transitiveExport = true
        }
    }
}