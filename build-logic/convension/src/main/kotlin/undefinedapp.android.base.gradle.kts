import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    compileSdkVersion(Config.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
        versionCode = Config.getVersionCode()
        versionName = Config.getVersionName()

        buildConfigField("int", "VERSION_CODE", "$versionCode")
        buildConfigField("String", "VERSION_NAME", "\"$versionName\"")
    }

    compileOptions {
        setSourceCompatibility(Config.JAVA_SOURCE)
        setTargetCompatibility(Config.JAVA_TARGET)
    }

    viewBinding {
        isEnabled = true
    }

    pluginManager.apply {
        apply("org.jetbrains.kotlin.android")
        apply("org.jetbrains.kotlin.kapt")
    }
}

fun libs(lib: String): String {
    val versionCatalog = project.extensions.getByType(VersionCatalogsExtension::class).named("libs")
    val dep = versionCatalog.findLibrary(lib).get().get()
    return "${dep.module.group}:${dep.module.name}:${dep.versionConstraint.displayName}"
}
