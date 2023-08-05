package com.ink1804.dev.feature.profile.ui

import androidx.annotation.StringRes
import com.ink1804.dev.undefinedapp.R

enum class ProfileListItemType(@StringRes val textRes: Int) {
    Settings(R.string.profile_item_settings),
    About(R.string.profile_item_about),
    RateUs(R.string.profile_item_rate_us),
    SignIn(R.string.profile_item_sign_in),
    SignOut(R.string.profile_item_sign_out);
}

data class ProfileListItem(
    val type: ProfileListItemType,
    @StringRes val textRes: Int = type.textRes
)