plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.opoojkk.glancesp"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // 核心依赖
    implementation(libs.androidx.core.ktx)
    // UI 相关依赖（库的入口 Activity 需要）
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
}

group = "com.github.opoojkk"
version = "0.0.1"

android {
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.github.opoojkk"
                artifactId = "glancesp"
                version = "0.0.1"

                pom {
                    name.set("GlanceSP")
                    description.set("A lightweight Android library for viewing and managing SharedPreferences data with minimal dependencies")
                    url.set("https://github.com/opoojkk/glanceapp")

                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }

                    developers {
                        developer {
                            id.set("opoojkk")
                            name.set("opoojkk")
                            email.set("opoojkk101@gmail.com")
                        }
                    }
                }
            }
        }
    }
}