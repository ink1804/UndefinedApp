import com.android.build.gradle.BaseExtension

plugins{
}

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

    dependencies {
        add("implementation", libs("daggerLib"))
        add("implementation", libs("daggerAndroidLib"))
        add("implementation", libs("timberLib"))
        add("kapt", libs("daggerKaptLib"))
        add("kapt", libs("daggerKaptProcessorLib"))
    }
}

fun libs(lib: String): String {
    val versionCatalog = project.extensions.getByType(VersionCatalogsExtension::class).named("libs")
    val dep = versionCatalog.findLibrary(lib).get().get()
    return "${dep.module.group}:${dep.module.name}:${dep.versionConstraint.displayName}"
}
