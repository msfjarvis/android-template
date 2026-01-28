/*
 * Copyright © 2025 Harsh Shandilya.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
@file:Suppress("UnstableApiUsage")

plugins {
  id("dev.msfjarvis.android-application")
  id("dev.msfjarvis.rename-artifacts")
  id("dev.msfjarvis.kotlin-android")
  id("dev.msfjarvis.versioning-plugin")
  alias(libs.plugins.licensee)
  alias(libs.plugins.kotlin.composeCompiler)
  alias(libs.plugins.dependencyAnalysis)
}

android {
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

licensee {
  allow("Apache-2.0")
  allow("MIT")
}
