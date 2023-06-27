import com.android.build.gradle.BaseExtension
import org.gradle.kotlin.dsl.configure

configure<BaseExtension> {
    compileSdkVersion(Config.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
        versionCode = Config.getVersionCode()
        versionName = Config.getVersionName()
    }
    //todo move to constants
    namespace = "com.ink1804.dev.undefinedapp"

    compileOptions {
        setSourceCompatibility(Config.JAVA_SOURCE)
        setTargetCompatibility(Config.JAVA_TARGET)
    }
}