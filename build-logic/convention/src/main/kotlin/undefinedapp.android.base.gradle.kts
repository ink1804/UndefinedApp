import com.android.build.gradle.BaseExtension

plugins{
    id("org.jetbrains.kotlin.android")
}

configure<BaseExtension> {
    compileSdkVersion(Config.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
        versionCode = Config.getVersionCode()
        versionName = Config.getVersionName()

        //move to app module
//        buildConfigField("int", "VERSION_CODE", "$versionCode")
//        buildConfigField("String", "VERSION_NAME", "\"$versionName\"")
    }
    namespace = "com.ink1804.dev.undefinedapp"
    compileOptions {
        setSourceCompatibility(Config.JAVA_SOURCE)
        setTargetCompatibility(Config.JAVA_TARGET)
    }

    dependencies {
        add("implementation", libs("koinLib"))
        add("implementation", libs("koinComposeLib"))
        add("implementation", libs("timberLib"))
    }
}

fun libs(lib: String): String {
    val versionCatalog = project.extensions.getByType(VersionCatalogsExtension::class).named("libs")
    val dep = versionCatalog.findLibrary(lib).get().get()
    return "${dep.module.group}:${dep.module.name}:${dep.versionConstraint.displayName}"
}
