plugins {
    id("undefinedapp.android.library")
}

android {

}

dependencies {
    api(libs.retrofitLib)
    implementation(libs.moshiConverterLib)
    implementation(libs.moshiLib)
    implementation(libs.moshiKotlinLib)
    api(libs.okhttpLoggingLib)
    implementation(libs.okhttpLib)
    debugApi(libs.chuckerLib)
    releaseApi(libs.chuckerNoOpLib)
    kapt(libs.moshiCodegenKaptLib)
}