plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {

    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        applicationId = Configs.appId
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        getByName(Configs.buildType_release) {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    flavorDimensions(Configs.dimensionEnv)

    productFlavors {

        val STRING_LOWER: String = "string"
        val STRING: String = "String"
        val APP_NAME: String = "app_name"

        create(Configs.dev) {
            dimension = Configs.dimensionEnv
            applicationIdSuffix = Configs.devAppSuffix
            versionNameSuffix = Configs.devVersionNameSuffix

            buildConfigField(STRING, "IMAGE_URL", "\"https://storage.googleapis.com/wotv/\"")
            buildConfigField(STRING, "AVATAR_PATH", "\"avatar/\"")
            buildConfigField(STRING, "ESPER_PATH", "\"esper/\"")
            buildConfigField(STRING, "EQUIPMENT_PATH", "\"equipment/\"")
            buildConfigField(STRING, "VISION_CARD_PATH", "\"visioncard/\"")
            buildConfigField(STRING, "RARITY_PATH", "\"rarity/\"")
            buildConfigField(STRING, "ELEMENT_PATH", "\"element/\"")
            resValue(STRING_LOWER, APP_NAME, "WOTV-Calc$versionNameSuffix")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation (project(":api"))

    implementation(Libraries.kotlinstdlib)
    implementation(Libraries.coreKtx)
    implementation(Libraries.appCompat)
    implementation(Libraries.materialDesign)
    implementation(Libraries.constraintLayout)
    testImplementation(Libraries.junit)
    androidTestImplementation(Libraries.junitAndroidX)
    androidTestImplementation(Libraries.espresso)

    implementation(Libraries.rxAndroid)
    implementation(Libraries.rxJava)
    implementation(Libraries.rxKotlin)

    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)
    implementation(Libraries.fragment)
    implementation(Libraries.viewModelSavedState)

    implementation(Libraries.koin)
    implementation(Libraries.koinScope)
    implementation(Libraries.koinViewModel)

    implementation(Libraries.dimensSdp)
    implementation(Libraries.glide)

}