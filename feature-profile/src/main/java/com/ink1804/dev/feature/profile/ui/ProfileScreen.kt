package com.ink1804.dev.feature.profile.ui

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ink1804.dev.common.ui.BuildConfig
import com.ink1804.dev.common.ui.R
import com.ink1804.dev.common.ui.UndColors
import com.ink1804.dev.common.ui.base.DefaultDimens
import com.ink1804.dev.common.ui.base.collect
import com.ink1804.dev.common.ui.component.NoRippleInteractionSource
import com.ink1804.dev.common.ui.component.UndButton
import com.ink1804.dev.common.ui.component.UndImage
import com.ink1804.dev.common.ui.component.bounceClick
import org.koin.androidx.compose.koinViewModel

private object ProfileDimens {
    val iconSize = 100.dp
    const val cardBounceClick = 0.97f
}

@Composable
@Preview
fun Preview() {
    Content()
}

@Composable
fun ProfileRedesignScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val googleAuthResultLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.handleGoogleLogin(it.data)
        }

    //todo create extension for this call
    LaunchedEffect(key1 = "signInFlow") {
        viewModel.signInFlow.collect {
            googleAuthResultLauncher.launch(it)
        }
    }

    val authState = remember { viewModel.isAuthorized }

    Content(
        authState.value,
        onBackClick = {},
        onSettingsClick = { viewModel.openSettingsScreen() },
        onSignInClick = { viewModel.onSignInClick() },
        onAboutClick = { viewModel.openAboutScreen() },
        onSupportClick = {
            Toast.makeText(context, "Support Click", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
internal fun Content(
    isAuthorized: Boolean = false,
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    onAboutClick: () -> Unit = {},
    onSupportClick: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = UndColors.Level1Color)
    ) {
        val (
            cardAccount,
            textVersion,
            cardSupport,
            cardAbout,
        ) = createRefs()

        AccountCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DefaultDimens.paddingMedium)
                .constrainAs(cardAccount) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick,
            onSignInClick = onSignInClick,
            isAuthorized = isAuthorized,
        )

        ActionCard(
            modifier = Modifier
                .padding(top = DefaultDimens.paddingLarge)
                .constrainAs(cardSupport) {
                    top.linkTo(cardAccount.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            text = stringResource(id = R.string.profile_item_support),
            onClick = onSupportClick
        )

        ActionCard(
            modifier = Modifier
                .padding(vertical = DefaultDimens.paddingMedium)
                .constrainAs(cardAbout) {
                    top.linkTo(cardSupport.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            text = stringResource(id = R.string.profile_item_about),
            onClick = onAboutClick
        )

        Text(
            modifier = Modifier
                .constrainAs(textVersion) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(ProfileScreensDimens.versionTextPadding),
            text = "V ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
            color = UndColors.TitleColor
        )
    }
}

@Composable
fun AccountCard(
    modifier: Modifier,
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    isAuthorized: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(DefaultDimens.shapeRadiusBig),
        backgroundColor = UndColors.Level2Color,
        modifier = modifier
    ) {
        ConstraintLayout {
            val (
                text,
                backButton,
                settingButton,
                profileImage,
                userName,
                signInButton,
            ) = createRefs()
            UndImage(
                modifier = Modifier
                    .padding(DefaultDimens.paddingBig)
                    .bounceClick()
                    .constrainAs(backButton) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                resId = R.drawable.ic_24_arrow_back,
                onClick = onBackClick
            )

            UndImage(
                modifier = Modifier
                    .padding(DefaultDimens.paddingBig)
                    .bounceClick()
                    .constrainAs(settingButton) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                resId = R.drawable.ic_24_settings,
                onClick = onSettingsClick
            )

            Text(
                text = stringResource(id = R.string.screen_account),
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(backButton.top, margin = DefaultDimens.paddingBig)
                    bottom.linkTo(backButton.bottom, margin = DefaultDimens.paddingBig)
                    start.linkTo(backButton.end, margin = DefaultDimens.paddingBig)
                    end.linkTo(settingButton.start, margin = DefaultDimens.paddingBig)
                },
                color = UndColors.TitleColor,
            )

            UndImage(
                modifier = Modifier
                    .constrainAs(profileImage) {
                        top.linkTo(backButton.bottom, DefaultDimens.paddingMedium)
                        start.linkTo(parent.start, DefaultDimens.paddingBig)
                        bottom.linkTo(parent.bottom, DefaultDimens.paddingBig)
                    },
                resId = R.drawable.ic_24_profile_outline,
                size = ProfileDimens.iconSize,
                cornerRadius = DefaultDimens.shapeRadiusLarge,
            )

            Text(
                modifier = Modifier.constrainAs(userName) {
                    top.linkTo(profileImage.top)
                    start.linkTo(profileImage.end, DefaultDimens.paddingBig)
                    bottom.linkTo(signInButton.top)
                },
                text = stringResource(id = R.string.profile_undefined_user),
                color = UndColors.TitleColor,
            )

            UndButton(
                text = stringResource(id = if (isAuthorized) R.string.profile_log_out else R.string.profile_sign_in),
                modifier = Modifier
                    .constrainAs(signInButton) {
                        top.linkTo(userName.bottom)
                        bottom.linkTo(profileImage.bottom)
                        start.linkTo(profileImage.end, DefaultDimens.paddingBig)
                    }
                    .bounceClick(),
                onClick = onSignInClick
            )
        }
    }
}

@Composable
fun ActionCard(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(DefaultDimens.shapeRadiusMiddle),
        backgroundColor = UndColors.Level2Color,
        modifier = modifier
            .bounceClick(ProfileDimens.cardBounceClick)
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null
            ) {
                onClick.invoke()
            }
            .padding(horizontal = DefaultDimens.paddingMedium)
    ) {
        Text(
            modifier = Modifier.padding(ProfileScreensDimens.listItemTextPadding),
            text = text,
            color = UndColors.TitleColor
        )
    }
}
