/*
 * Copyright © 2025 Harsh Shandilya.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.ApplicationExtension
import dev.msfjarvis.gradle.addTestDependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag

plugins {
  id("dev.msfjarvis.android-application")
  id("dev.msfjarvis.rename-artifacts")
  id("dev.msfjarvis.kotlin-android")
  id("dev.msfjarvis.kotlin-kapt")
  id("dev.msfjarvis.versioning-plugin")
  alias(libs.plugins.android.junit5)
  alias(libs.plugins.anvil)
  alias(libs.plugins.whetstone)
  alias(libs.plugins.licensee)
  alias(libs.plugins.kotlin.composeCompiler)
  alias(libs.plugins.dependencyAnalysis)
}

// Directly using the generated `android` accessor lights up bright red
extensions.configure<ApplicationExtension> {
  namespace = "dev.msfjarvis.android.template"
  defaultConfig.applicationId = "dev.msfjarvis.android.template"
  defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  buildFeatures.compose = true
  buildTypes.create("internal") {
    matchingFallbacks += "release"
    signingConfig = signingConfigs["debug"]
    applicationIdSuffix = ".internal"
    isDebuggable = true
  }
}

composeCompiler {
  featureFlags.addAll(
    ComposeFeatureFlag.OptimizeNonSkippingGroups,
    ComposeFeatureFlag.PausableComposition,
  )
}

licensee {
  allow("Apache-2.0")
  allow("MIT")
}

whetstone { addOns { compose.set(true) } }

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(platform(libs.kotlinx.coroutines.bom))

  addTestDependencies(project)
}
