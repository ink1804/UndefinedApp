plugins {
    id("com.android.library")
}

android {

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.COMPOSE_KOTLIN_COMPILER
    }

    dependencies {

    }

}

fun libs(lib: String): String {
    val versionCatalog = project.extensions.getByType(VersionCatalogsExtension::class).named("libs")
    val dep = versionCatalog.findLibrary(lib).get().get()
    return "${dep.module.group}:${dep.module.name}:${dep.versionConstraint.displayName}"
}
