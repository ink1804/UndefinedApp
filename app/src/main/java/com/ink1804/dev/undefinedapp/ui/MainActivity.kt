package com.ink1804.dev.undefinedapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ink1804.dev.common.ui.NavControllerProvider
import com.ink1804.dev.common.ui.UndColors
import com.ink1804.dev.undefinedapp.di.koinMainActivityModule
import com.ink1804.dev.undefinedapp.ui.theme.UndefinedappTheme
import org.koin.androidx.compose.inject
import org.koin.core.context.loadKoinModules

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(koinMainActivityModule(this))

        setContent { App() }
    }
}

@Composable
fun App() {
    UndefinedappTheme {
        NavigationConfiguration()
        AppUi()
    }
}

@Composable
fun AppUi() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(UndColors.StatusBarColor, false)
    systemUiController.setNavigationBarColor(UndColors.NavigationBarColor)
}

@Composable
fun NavigationConfiguration() {
    val navHostProvider: NavControllerProvider by inject()

    val appState = rememberAppState()
    navHostProvider.provide(appState.navController)

    Scaffold(
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                BottomNavigation(
                    navController = appState.navController,
                    appState = appState
                )
            }
        },
        scaffoldState = appState.scaffoldState
    ) { innerPadding ->
        NavigationGraph(navController = appState.navController, innerPadding)
    }
}

@Composable
fun BottomNavigation(
    navController: NavController,
    appState: AppState
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        appState.bottomBarTabs.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = screen.iconId), contentDescription = null) },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route?.contains(screen.route.screenName) ?: false } == true,
                onClick = { appState.navigateToBottomBarRoute(screen.route.screenName) }
            )
        }
    }
}

@Composable
@Preview
fun Preview() {
    val appState = rememberAppState()
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                BottomNavigation(
                    navController = navController,
                    appState = appState
                )
            }
        },
        scaffoldState = appState.scaffoldState
    ) { innerPadding ->
        NavigationGraph(navController = navController, innerPadding)
    }
}

