plugins {
    id("kmm-base")
}

kotlin {
    android()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared-common-network"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktorCoreLib)
                implementation(libs.koinLib)
                implementation(libs.ktorNegotiationLib)
                implementation(libs.ktorSerializationLib)
                implementation(libs.kotlinCoroutinesCoreLib)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.koinAndroidLib)
                api(libs.ktorOkhttpLib)
                api(libs.okhttpLoggingLib)

                //todo debug/release
                api(libs.chuckerLib)
//                releaseApi(libs.chuckerNoOpLib)
            }
        }

        val iosSimulatorArm64Main by getting {
            dependencies {
                dependsOn(commonMain)
                implementation(libs.ktorDarwinLib)
            }
        }
        val iosMain by getting {
            dependencies {
                dependsOn(commonMain)
                implementation(libs.ktorDarwinLib)
            }
        }
    }
}
