package com.ink1804.dev.undefinedapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ink1804.dev.feature.main.ui.MainScreen
import com.ink1804.dev.feature.splash.ui.SplashScreen
import com.ink1804.dev.undefinedapp.navigation.AppDestination

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppDestination.Splash.screenName) {
        composable(AppDestination.Main) { MainScreen() }
        composable(AppDestination.Splash) { SplashScreen() }
    }
}

fun NavGraphBuilder.composable(
    route: AppDestination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    this.composable(
        route = route.screenName,
        arguments = arguments,
        deepLinks = deepLinks,
        content = content
    )

}