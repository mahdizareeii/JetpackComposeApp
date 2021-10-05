object Libs {

    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"

    //KTX
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX_VERSION}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX_VERSION}"
    const val LIFE_CYCLE_RUNTIME_KTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFE_CYCLE_RUNTIME_KTX_VERSION}"
    const val VIEW_MODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.VIEW_MODEL_KTX_VERSION}"
    const val LIVE_DATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIVE_DATA_KTX_VERSION}"

    //Test
    const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
    const val TEST_CORE = "androidx.test:core:${Versions.TEST_CORE_VERSION}"
    const val MOCKITO = "org.mockito:mockito-core:${Versions.MOCKITO_VERSION}"
    const val EXT_TEST_JUNIT = "androidx.test.ext:junit:${Versions.EXT_TEST_JUNIT_VERSION}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE_VERSION}"
    const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_VERSION}"

    //Jetpack compose
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:1.3.1"
    const val COMPOSE = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_UI_TOOLING_PREVIEW =
        "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_VERSION}"
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:2.4.0-alpha08"
    const val VIEW_MODEL_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"

    //Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSIONS}"
    const val RETROFIT_CONVERTER =
        "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_VERSIONS}"

    //Hilt
    const val HILT = "com.google.dagger:hilt-android:${Versions.HILT_VERSION}"
    const val DAGGER_HILT_COMPILER =
        "com.google.dagger:hilt-android-compiler:${Versions.HILT_VERSION}"
    const val HILT_COMPILER = "androidx.hilt:hilt-compiler:${Versions.HILT_COMPILER_VERSION}"
    const val HILT_NAVIGATION_COMPOSE =
        "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION_COMPOSE_VERSION}"
    const val HILT_VIEW_MODEL =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_VIEW_MODEL_VERSION}"

    //Coil
    const val COIL = "io.coil-kt:coil-compose:${Versions.COIL_VERSION}"
}