package com.adriyo.newsapp

import androidx.navigation.NavController
import com.adriyo.newsapp.feature.app.Route
import com.google.common.truth.Truth.assertThat

/**
 * https://github.dev/google-developer-training/basic-android-kotlin-compose-training-cupcake
 */
fun NavController.assertCurrentRouteName(expectedRouteName: Route) {
    assertThat(currentBackStackEntry?.destination?.route).isEqualTo(expectedRouteName)
}