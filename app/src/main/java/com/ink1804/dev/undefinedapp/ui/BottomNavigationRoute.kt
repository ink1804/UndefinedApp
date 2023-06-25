package com.ink1804.dev.undefinedapp.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ink1804.dev.common.ui.R.drawable
import com.ink1804.dev.common.ui.R.string
import com.ink1804.dev.undefinedapp.navigation.AppDestination

sealed class BottomNavigationRoute(
    val route: AppDestination,
    @StringRes val resourceId: Int,
    @DrawableRes val iconId: Int
) {
    object Main : BottomNavigationRoute(
        AppDestination.Main,
        string.bottom_home,
        drawable.ic_24_home_filled
    )

    object Map : BottomNavigationRoute(
        AppDestination.Map,
        string.bottom_map,
        drawable.ic_24_map_filled
    )

    object Profile : BottomNavigationRoute(
        AppDestination.Profile,
        string.bottom_profile,
        drawable.ic_24_profile_filled
    )
}