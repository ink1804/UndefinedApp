package com.ink1804.dev.feature.about.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.ink1804.dev.common.ui.BaseScreen

@Composable
fun AboutScreen(

) {
    BaseScreen(
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { }) {
            Text(text = "About")
        }
    }
}

@Composable
@Preview
fun Preview() {
    AboutScreen()
}

