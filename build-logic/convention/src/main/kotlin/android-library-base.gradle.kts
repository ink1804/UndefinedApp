import com.android.build.gradle.BaseExtension

plugins{
    id("org.jetbrains.kotlin.android")
    id("android-base")
}

configure<BaseExtension> {
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
