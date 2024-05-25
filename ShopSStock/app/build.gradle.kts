plugins {
    id("com.android.application")
}

android {
    namespace = "com.tahirova_ain1.shopsstock"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tahirova_ain1.shopsstock"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("androidx.navigation:navigation-fragment:2.7.7")
    implementation ("androidx.navigation:navigation-ui:2.7.7")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    //расширение возможностей фрагмента:
    implementation ("androidx.fragment:fragment:1.6.2")
    // круглое изображение
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    //Возможность преобразовать объект в необходимый формат:
    implementation ("androidx.multidex:multidex:2.0.1")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    //JsonConverter
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.google.code.gson:gson:2.8.6")
    // Okhttp
    implementation ("com.squareup.okhttp3:okhttp:4.9.1")

    implementation ("com.squareup.picasso:picasso:2.71828")

}