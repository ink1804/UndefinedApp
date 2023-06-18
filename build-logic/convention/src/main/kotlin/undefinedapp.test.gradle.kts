plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    dependencies {
        add("api", libs("kotestAssertionsLib"))
        add("api", libs("kotestRunnerJunitLib"))
        add("api", libs("kotestPropertyJvmLib"))
        add("api", libs("mockkLib"))
    }
}

fun libs(lib: String): String {
    val versionCatalog = project.extensions.getByType(VersionCatalogsExtension::class).named("libs")
    val dep = versionCatalog.findLibrary(lib).get().get()
    return "${dep.module.group}:${dep.module.name}:${dep.versionConstraint.displayName}"
}
