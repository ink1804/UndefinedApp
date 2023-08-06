package com.ink1804.dev.common.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

//ref: https://blog.canopas.com/jetpack-compose-cool-button-click-effects-c6bbecec7bcb

enum class ButtonState { Pressed, Idle }

fun Modifier.bounceClick(scale: Float = 0.9f) = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scaleState by animateFloatAsState(if (buttonState == ButtonState.Pressed) scale else 1f)

    this
        .graphicsLayer {
            scaleX = scaleState
            scaleY = scaleState
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}