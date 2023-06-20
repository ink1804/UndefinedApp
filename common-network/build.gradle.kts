plugins {
    id("undefinedapp.android.library")
    id("undefinedapp.test")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {

}

dependencies {
    api(libs.retrofitLib)
    api(libs.okhttpLoggingLib)
    implementation(libs.okhttpLib)

    implementation(libs.kotlinSerializationLib)
    implementation(libs.kotlinConverterLib)

    debugApi(libs.chuckerLib)
    releaseApi(libs.chuckerNoOpLib)
}