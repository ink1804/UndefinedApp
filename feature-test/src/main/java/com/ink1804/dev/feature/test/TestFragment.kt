package com.ink1804.dev.feature.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CompView() {
    Box(modifier = Modifier.fillMaxSize()) {
        PqGradientViewCompose.Compose(


        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "asda")
            Text(text = "asdaasd")
            Button(onClick = { }) {
                Text(text = "button")
            }
        }
    }

}

@Composable
@Preview
fun CompPewview() {
    CompView()
}

object PqGradientViewCompose {

    private val ALPHA_VALUES = listOf(
        0.0f, 0.0086f, 0.0355f, 0.0817f,
        0.1474f, 0.2318f, 0.3319f, 0.4427f,
        0.5573f, 0.6681f, 0.7682f, 0.8526f,
        0.9183f, 0.9645f, 0.9914f, 1.0f
    )

    @Composable
    fun Compose(
        gravity: Gravity = Gravity.TOP,
        opacity: Opacity = Opacity.FULL
    ) {
        Box(
            Modifier.background(
                brush = getGradientBrush(gravity, opacity, Color.Black)
            ).fillMaxSize()
        )
    }

    fun getGradientBrush(
        gravity: Gravity,
        opacity: Opacity,
        color: Color
    ): Brush {
        val valuedColors = if (gravity.isReversed) {
            ALPHA_VALUES.asReversed()
        } else {
            ALPHA_VALUES
        }.map { color.copy(alpha = it * opacity.value) }

        return if (gravity.isVertical) {
            Brush.verticalGradient(valuedColors)
        } else {
            Brush.horizontalGradient(valuedColors)
        }
    }

    enum class Gravity(val isVertical: Boolean, val isReversed: Boolean) {
        TOP(true, true),
        RIGHT(false, false),
        BOTTOM(true, false),
        LEFT(false, true)
    }

    enum class Opacity(val value: Float) {
        FULL(1.0f),
        MEDIUM(0.6f),
        LOW(0.4f)
    }
}
