plugins {
    id("undefinedapp.android.library.compose")
}

android {
    namespace = "com.ink1804.dev.common.ui"
    defaultConfig{
        buildConfigField("int", "VERSION_CODE", "${Config.getVersionCode()}")
        buildConfigField("String", "VERSION_NAME", "\"${Config.getVersionName()}\"")
    }
}

dependencies {
}