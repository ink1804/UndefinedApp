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
import androidx.compose.material.Button
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
import com.ink1804.dev.common.ui.BaseScreen
import com.ink1804.dev.common.ui.BuildConfig
import com.ink1804.dev.common.ui.R
import com.ink1804.dev.common.ui.UndColors
import com.ink1804.dev.common.ui.base.collect
import com.ink1804.dev.common.ui.component.UndImage
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
        UndImage(resId = R.drawable.ic_16_arrow_back)
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
    ProfileRedesignScreen()
}

@Composable
fun ProfileRedesignScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = UndColors.Level1Color)
    ) {
        val (
            text,
            card,
            backButton,
            settingButton,
            profileImage,
            userName,
            signInButton,
        ) = createRefs()

        Card(
            shape = RoundedCornerShape(24.dp),
            backgroundColor = UndColors.Level2Color,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }) {
            ConstraintLayout {
                UndImage(
                    modifier = Modifier
                        .padding(16.dp)
                        .constrainAs(backButton) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                        .clickable {

                        },
                    resId = R.drawable.ic_16_arrow_back
                )

                UndImage(
                    modifier = Modifier
                        .padding(16.dp)
                        .constrainAs(settingButton) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                        .clickable {

                        },
                    resId = R.drawable.ic_16_settings
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
                            top.linkTo(backButton.bottom, 16.dp)
                            start.linkTo(parent.start, 16.dp)
                            bottom.linkTo(parent.bottom, 16.dp)
                        },
                    resId = R.drawable.ic_24_profile_outline,
                    size = 64.dp,
                    cornerRadius = 16.dp
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

                Button(
                    modifier = Modifier
                        .constrainAs(signInButton) {
                            top.linkTo(userName.bottom)
                            bottom.linkTo(profileImage.bottom)
                            start.linkTo(profileImage.end, 16.dp)
                        },
                    onClick = { /*TODO*/ }) {
                    Text(text = "SignIn")
                }
            }
        }
    }
}
