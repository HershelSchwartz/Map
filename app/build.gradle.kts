plugins {
 id("com.android.application")
 id("org.jetbrains.kotlin.android")
}
android {
 namespace = "com.hershel.map"
 compileSdk = 35
 defaultConfig {
  applicationId = "com.hershel.map"
  minSdk = 26
  targetSdk = 35
  versionCode = 1
  versionName = "0.1.0"
  resValue("string", "mapbox_access_token", providers.gradleProperty("MAPBOX_PUBLIC_TOKEN").orElse(System.getenv("MAPBOX_PUBLIC_TOKEN") ?: "").get())
 }
 compileOptions {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
 }
 kotlinOptions { jvmTarget = "17" }
}
dependencies {
 implementation("com.mapbox.maps:android:11.13.1")
 implementation("androidx.activity:activity-ktx:1.10.1")
 implementation("androidx.core:core-ktx:1.16.0")
}