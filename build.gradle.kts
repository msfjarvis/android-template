/*
 * Copyright © Harsh Shandilya.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
plugins {
  id("dev.msfjarvis.spotless")
  id("dev.msfjarvis.versions")
  id("dev.msfjarvis.kotlin-common")
  alias(libs.plugins.dependencyAnalysis)
  alias(libs.plugins.gradle.versions)
}
