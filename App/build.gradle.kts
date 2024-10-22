buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("com.google.dagger.hilt.android") version "2.41" apply false
    // Kapt debe estar debajo de Dagger-
    id ("org.jetbrains.kotlin.kapt") version "1.7.20"
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false

}