package com.ink1804.dev.undefinedapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ink1804.dev.common.ui.NavControllerProvider
import com.ink1804.dev.undefinedapp.ui.theme.UndefinedappTheme
import org.koin.androidx.compose.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
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
//    val systemUiController = rememberSystemUiController()
//    systemUiController.setStatusBarColor(Color.Transparent, true)
//    systemUiController.setNavigationBarColor(Color.Green)
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomNavigation {

        }
    }
}

@Composable
fun NavigationConfiguration() {
    val navHostProvider: NavControllerProvider by inject()
    val navController = rememberNavController().also {
        navHostProvider.provide(it)
    }

    AppNavHost(navController)
}

@Composable
@Preview
fun Preview() {
    UndefinedappTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(onClick = { }) {
                Text(text = "qq")
            }
        }

    }
}

