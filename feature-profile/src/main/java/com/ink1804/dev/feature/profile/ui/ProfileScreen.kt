package com.ink1804.dev.feature.profile.ui

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ink1804.dev.common.ui.BaseScreen
import com.ink1804.dev.common.ui.BuildConfig
import com.ink1804.dev.common.ui.R
import com.ink1804.dev.common.ui.UndColors
import com.ink1804.dev.common.ui.base.collect
import com.ink1804.dev.common.ui.component.NoRippleInteractionSource
import com.ink1804.dev.common.ui.component.UndButton
import com.ink1804.dev.common.ui.component.UndImage
import com.ink1804.dev.common.ui.component.bounceClick
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {
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

    BaseScreen(
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.menuItems) {
                ListItem(
                    it,
                    onClick = viewModel::onItemClick
                )
            }
        }
        Text(
            modifier = Modifier.padding(ProfileScreensDimens.versionTextPadding),
            text = "V ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
        )
        UndImage(resId = R.drawable.ic_24_arrow_back, onClick = {})
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItem(item: ProfileListItem, modifier: Modifier = Modifier, onClick: (ProfileListItemType) -> Unit) {
    val context = LocalContext.current
    Row(
        modifier
            .fillMaxWidth()
            .padding(
                top = ProfileScreensDimens.listItemPadding,
                start = ProfileScreensDimens.listItemPadding,
                end = ProfileScreensDimens.listItemPadding
            )
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            backgroundColor = UndColors.Level2Color,
            onClick = {
                onClick.invoke(item.type)
                Toast.makeText(context, "Click on ${item.type.name}", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(
                modifier = Modifier.padding(ProfileScreensDimens.listItemTextPadding),
                text = stringResource(id = item.textRes),
                color = UndColors.TitleColor
            )
        }
    }
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

    Content(
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
                .padding(8.dp)
                .constrainAs(cardAccount) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick,
            onSignInClick = onSignInClick,
        )

        ActionCard(
            modifier = Modifier
                .padding(top = 24.dp)
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
                .padding(vertical = 8.dp)
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
) {
    Card(
        shape = RoundedCornerShape(24.dp),
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
                    .padding(16.dp)
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
                    .padding(16.dp)
                    .bounceClick()
                    .constrainAs(settingButton) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                resId = R.drawable.ic_24_settings,
                onClick = onSettingsClick
            )

            Text(
                "Account",
                Modifier.constrainAs(text) {
                    top.linkTo(backButton.top, margin = 16.dp)
                    bottom.linkTo(backButton.bottom, margin = 16.dp)
                    start.linkTo(backButton.end, margin = 16.dp)
                    end.linkTo(settingButton.start, margin = 16.dp)
                },
                color = UndColors.TitleColor,
            )

            UndImage(
                modifier = Modifier
                    .constrainAs(profileImage) {
                        top.linkTo(backButton.bottom, 8.dp)
                        start.linkTo(parent.start, 16.dp)
                        bottom.linkTo(parent.bottom, 16.dp)
                    },
                resId = R.drawable.ic_24_profile_outline,
                size = 100.dp,
                cornerRadius = 32.dp,
            )

            Text(
                modifier = Modifier.constrainAs(userName) {
                    top.linkTo(profileImage.top)
                    start.linkTo(profileImage.end, 16.dp)
                    bottom.linkTo(signInButton.top)
                },
                text = "Undefined User",
                color = UndColors.TitleColor,
            )

            UndButton(
                text = "Sing In",
                modifier = Modifier
                    .constrainAs(signInButton) {
                        top.linkTo(userName.bottom)
                        bottom.linkTo(profileImage.bottom)
                        start.linkTo(profileImage.end, 16.dp)
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
        shape = RoundedCornerShape(12.dp),
        backgroundColor = UndColors.Level2Color,
        modifier = modifier
            .bounceClick(0.97f)
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null
            ) {
                onClick.invoke()
            }
            .padding(horizontal = 8.dp)
    ) {
        Text(
            modifier = Modifier.padding(ProfileScreensDimens.listItemTextPadding),
            text = text,
            color = UndColors.TitleColor
        )
    }
}
