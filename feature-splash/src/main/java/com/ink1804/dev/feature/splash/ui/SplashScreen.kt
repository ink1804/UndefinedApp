package com.ink1804.dev.feature.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ink1804.dev.common.ui.BaseScreen
import com.ink1804.dev.common.ui.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel()
) {
    BaseScreen(Modifier.background(Color.Blue)) {
        Column() {
            Image(
                modifier = Modifier.size(100.dp, 100.dp),
                painter = painterResource(id = R.drawable.ic_24_question),
                contentDescription = null
            )
        }
    }
    viewModel.openMainScreen()
}

@Composable
@Preview
fun Preview() {
    BaseScreen(Modifier.background(Color.Blue)) {
        Column() {
            Image(
                modifier = Modifier.size(100.dp, 100.dp),
                painter = painterResource(id = R.drawable.ic_24_question),
                contentDescription = null
            )
        }
    }
}

