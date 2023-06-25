package com.ink1804.dev.feature.map.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.ink1804.dev.common.ui.BaseScreen

@Composable
fun MapScreen() {
    BaseScreen(
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { }) {
            Text(text = "Map")
        }
    }
}

@Composable
@Preview
fun Preview() {
    MapScreen()
}
