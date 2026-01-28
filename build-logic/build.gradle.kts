/*
 * Copyright © Harsh Shandilya.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension

plugins {
  `kotlin-dsl`
  alias(libs.plugins.android.lint)
  alias(libs.plugins.dependencyAnalysis)
}

gradlePlugin {
  plugins {
    register("android-application") {
      id = "dev.msfjarvis.android-application"
      implementationClass = "dev.msfjarvis.gradle.ApplicationPlugin"
    }
    register("android-common") {
      id = "dev.msfjarvis.android-common"
      implementationClass = "dev.msfjarvis.gradle.AndroidCommonPlugin"
    }
    register("android-library") {
      id = "dev.msfjarvis.android-library"
      implementationClass = "dev.msfjarvis.gradle.LibraryPlugin"
    }
    register("kotlin-android") {
      id = "dev.msfjarvis.kotlin-android"
      implementationClass = "dev.msfjarvis.gradle.KotlinAndroidPlugin"
    }
    register("kotlin-common") {
      id = "dev.msfjarvis.kotlin-common"
      implementationClass = "dev.msfjarvis.gradle.KotlinCommonPlugin"
    }
    register("kotlin-jvm") {
      id = "dev.msfjarvis.kotlin-jvm"
      implementationClass = "dev.msfjarvis.gradle.KotlinJvmPlugin"
    }
    register("rename-artifacts") {
      id = "dev.msfjarvis.rename-artifacts"
      implementationClass = "dev.msfjarvis.gradle.RenameArtifactsPlugin"
    }
    register("spotless") {
      id = "dev.msfjarvis.spotless"
      implementationClass = "dev.msfjarvis.gradle.SpotlessPlugin"
    }
    register("versioning") {
      id = "dev.msfjarvis.versioning-plugin"
      implementationClass = "dev.msfjarvis.gradle.versioning.VersioningPlugin"
    }
    register("versions") {
      id = "dev.msfjarvis.versions"
      implementationClass = "dev.msfjarvis.gradle.DependencyUpdatesPlugin"
    }
  }
}

lint.baseline = project.file("lint-baseline.xml")

extensions.getByType<KotlinJvmExtension>().compilerOptions { jvmTarget.set(JvmTarget.JVM_21) }

tasks.withType<JavaCompile>().configureEach {
  sourceCompatibility = JavaVersion.VERSION_21.toString()
  targetCompatibility = JavaVersion.VERSION_21.toString()
}

dependencies {
  implementation(libs.build.agp)
  implementation(libs.build.cachefix)
  implementation(libs.build.kotlin.gradle)
  implementation(libs.build.semver)
  implementation(libs.build.sentry)
  implementation(libs.build.spotless)
  implementation(libs.build.vcu)

  // Expose the generated version catalog API to the plugin.
  implementation(files(libs::class.java.superclass.protectionDomain.codeSource.location))

  lintChecks(libs.androidx.lint.gradle)
}
