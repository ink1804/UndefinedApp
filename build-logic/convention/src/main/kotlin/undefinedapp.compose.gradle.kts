plugins {
    id("com.android.library")
    kotlin("android")
}

android {

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.COMPOSE_KOTLIN_COMPILER
    }

    dependencies {
        add("api", libs("composeUiLib"))
        add("api", libs("composeMaterialLib"))
        add("api", libs("composeUiToolingPreviewLib"))
        add("api", libs("composeUiToolingLib"))
    }

}

fun libs(lib: String): String {
    val versionCatalog = project.extensions.getByType(VersionCatalogsExtension::class).named("libs")
    val dep = versionCatalog.findLibrary(lib).get().get()
    return "${dep.module.group}:${dep.module.name}:${dep.versionConstraint.displayName}"
}
