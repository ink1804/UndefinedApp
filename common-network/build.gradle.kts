plugins {
    id("undefinedapp.android.library")
    id("undefinedapp.test")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {

}

dependencies {
    api(libs.ktorCoreLib)
    api(libs.ktorOkhttpLib)
    implementation(libs.ktorNegotiationLib)
    implementation(libs.ktorSerializationLib)

    api(libs.okhttpLoggingLib)

    debugApi(libs.chuckerLib)
    releaseApi(libs.chuckerNoOpLib)
}