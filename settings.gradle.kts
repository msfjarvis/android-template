/*
 * Copyright © Harsh Shandilya.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
@file:Suppress("UnstableApiUsage")

pluginManagement {
  repositories {
    exclusiveContent {
      forRepository { google() }
      filter {
        includeGroup("androidx.baselineprofile")
        includeGroup("androidx.benchmark")
        includeGroup("androidx.databinding")
        includeGroupAndSubgroups("androidx.navigation")
        includeGroup("com.google.testing.platform")
        includeGroupAndSubgroups("com.android")
      }
    }
    exclusiveContent {
      forRepository { gradlePluginPortal() }
      filter {
        includeModule("org.gradle.android.cache-fix", "org.gradle.android.cache-fix.gradle.plugin")
        includeModule("gradle.plugin.org.gradle.android", "android-cache-fix-gradle-plugin")
        includeModule("com.gradle", "develocity-gradle-plugin")
        includeModule("com.gradle.develocity", "com.gradle.develocity.gradle.plugin")
      }
    }
    includeBuild("build-logic")
    mavenCentral { mavenContent { releasesOnly() } }
  }
}

plugins {
  id("com.gradle.develocity") version "4.5.0"
}

develocity {
  buildScan {
    termsOfUseUrl = "https://gradle.com/help/legal-terms-of-use"
    termsOfUseAgree = if (System.getenv("GITHUB_WORKFLOW").isNullOrEmpty()) "no" else "yes"
    publishing.onlyIf { !System.getenv("GITHUB_WORKFLOW").isNullOrEmpty() }
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google {
      content {
        includeGroupAndSubgroups("androidx")
        includeGroupAndSubgroups("com.android")
        includeGroup("com.google.android.gms")
        includeModule("com.google.android.material", "material")
        includeGroup("com.google.testing.platform")
      }
    }
    maven("https://androidx.dev/storage/compose-compiler/repository") {
      name = "Compose Compiler Snapshots"
      content { includeGroup("androidx.compose.compiler") }
    }
    mavenCentral { mavenContent { releasesOnly() } }
  }
}

rootProject.name = "Android_Template"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("android")
