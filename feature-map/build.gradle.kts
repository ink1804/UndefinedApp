plugins {
    id("undefinedapp.android.library.compose")
    id("undefinedapp.test")
}

dependencies {
    implementation(project(":common-ui"))
    implementation(libs.composeMapsLib)
    implementation(libs.googleServicesMapsLib)
}