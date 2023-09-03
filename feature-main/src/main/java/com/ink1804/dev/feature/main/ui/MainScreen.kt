package com.ink1804.dev.feature.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ink1804.dev.common.ui.UndColors
import com.ink1804.dev.common.ui.component.UndButton
import com.ink1804.dev.feature.main.ui.MainItem.BannerItem
import com.ink1804.dev.feature.main.ui.MainItem.ListItem
import com.ink1804.dev.feature.main.ui.MainItem.SimpleItem

val items = mutableStateListOf(
    BannerItem(""),
    ListItem(listOf("", "", "", "", "")),
    SimpleItem(""),
    SimpleItem(""),
    SimpleItem(""),
    SimpleItem(""),
    SimpleItem(""),
    SimpleItem(""),
    SimpleItem(""),
    SimpleItem(""),
    SimpleItem(""),
    SimpleItem(""),
)

sealed class MainItem {
    class SimpleItem(val value: String) : MainItem()
    class ListItem(val value: List<String>) : MainItem()
    class BannerItem(val value: String) : MainItem()
}

@Composable
fun MainScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = UndColors.Level1Color)
    ) {
        val (
            discoveryList,
            tryButton
        ) = createRefs()

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(discoveryList) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            columns = GridCells.Fixed(2)
        ) {
            itemsIndexed(
                items = items,
                span = { index, item ->
                    val spanCount = if (item is BannerItem || item is ListItem) 2 else 1
                    GridItemSpan(spanCount)
                }
            ) { index, qwe ->
                when (qwe) {
                    is BannerItem -> BannerListItem()
                    is ListItem -> ListListItem(qwe.value)
                    is SimpleItem -> SimpleListItem()
                }

            }
        }

        UndButton(
            modifier = Modifier.padding(16.dp)
                .constrainAs(tryButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = "Plan your travel"
        )
    }
}


@Composable
fun BannerListItem() {
    Box(
        modifier = Modifier
            .size(300.dp)
            .padding(4.dp)
            .background(
                color = UndColors.Level2Color,
                shape = RoundedCornerShape(8.dp)
            ), contentAlignment = Alignment.Center
    ) {
        Text(text = "Banner", color = UndColors.TitleColor)
    }
}

@Composable
fun ListListItem(listItems: List<String>) {
    LazyRow {
        items(listItems) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .background(
                        color = UndColors.Level2Color,
                        shape = RoundedCornerShape(50.dp)
                    )
            )
        }
    }

}

@Composable
fun SimpleListItem() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp)
            .background(
                color = UndColors.Level2Color,
                shape = RoundedCornerShape(8.dp)
            )
    )
}


@Composable
@Preview
fun Preview() {
    MainScreen()
}

