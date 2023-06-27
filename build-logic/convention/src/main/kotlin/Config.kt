import java.io.FileInputStream
import java.util.Properties
import org.gradle.api.JavaVersion

object Config {
    const val COMPILE_SDK_VERSION = 33
    const val TARGET_SDK_VERSION = 33
    const val MIN_SDK_VERSION = 24

    const val COMPOSE_KOTLIN_COMPILER = "1.4.7"

    val JAVA_SOURCE = JavaVersion.VERSION_19
    val JAVA_TARGET = JavaVersion.VERSION_19
    val JVM_TARGET = JavaVersion.VERSION_19.toString()

    fun getVersionName(): String {
        return getVersionProperties()["version.name"].toString()
    }

    fun getVersionCode(): Int {
        return getVersionProperties()["version.code"].toString().toInt()
    }

    private fun getVersionProperties(): Properties {
        val input = FileInputStream("version.properties")
        return Properties().apply {
            load(input)
        }
    }
}