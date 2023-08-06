package com.ink1804.dev.common.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ink1804.dev.common.ui.R
import com.ink1804.dev.common.ui.UndColors

@Composable
fun UndImage(
    @DrawableRes resId: Int,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    cornerRadius: Dp = 8.dp,
) {
    Image(
        modifier = modifier
            .size(size)
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
    Box(modifier = Modifier) {
        UndImage(resId = R.drawable.ic_16_arrow_back)
    }
}