package com.ink1804.dev.common.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ink1804.dev.common.ui.R
import com.ink1804.dev.common.ui.UndColors
import org.w3c.dom.Text

@Composable
fun UndImage(
    @DrawableRes resId: Int,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    size: Dp = 44.dp,
    cornerRadius: Dp = 18.dp,
) {
    Image(
        modifier = modifier
            .size(size)
            .padding(1.dp)
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null
            ) {
                onClick.invoke()
            }
            .background(
                UndColors.Level3Color,
                shape = RoundedCornerShape(cornerRadius)
            ),
        painter = painterResource(id = resId),
        contentScale = ContentScale.Inside,
        colorFilter = ColorFilter.tint(UndColors.TitleColor),
        contentDescription = null,
    )
}

@Preview
@Composable
fun UndImagePreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        UndButton(
            text = "qweqwe",
            modifier = Modifier
        )
    }
}

@Composable
fun UndButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    cornerRadius: Dp = 8.dp,
) {
    Text(
        text = text,
        modifier = modifier
            .wrapContentWidth()
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null
            ) {
                onClick.invoke()
            }
            .background(
                UndColors.AccentColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .padding(
                vertical = 4.dp, horizontal = 12.dp
            ),
        textAlign = TextAlign.Center,
        color = UndColors.TitleOnAccentColor,
        style = TextStyle(
            fontSize = 16.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
//            baselineShift = BaselineShift(0.15f)
        )
    )
}
