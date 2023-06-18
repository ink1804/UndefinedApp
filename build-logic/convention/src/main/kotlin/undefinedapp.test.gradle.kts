plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    defaultConfig{
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    dependencies {
        add("testImplementation", libs("kotestAssertionsLib"))
        add("testImplementation", libs("kotestRunnerJunitLib"))
        add("testImplementation", libs("kotestPropertyJvmLib"))
        add("testImplementation", libs("mockkLib"))
    }
}

fun libs(lib: String): String {
    val versionCatalog = project.extensions.getByType(VersionCatalogsExtension::class).named("libs")
    val dep = versionCatalog.findLibrary(lib).get().get()
    return "${dep.module.group}:${dep.module.name}:${dep.versionConstraint.displayName}"
}
