plugins {
    id("undefinedapp.android.application")
}

android {
    defaultConfig {
        applicationId = "com.ink1804.dev.undefinedapp"
        setProperty("archivesBaseName", rootProject.name + "_v." + versionName)
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}