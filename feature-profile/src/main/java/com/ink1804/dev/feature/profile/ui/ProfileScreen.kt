package com.ink1804.dev.feature.profile.ui

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ink1804.dev.common.ui.AppColors.CardColor
import com.ink1804.dev.common.ui.AppColors.TextColor
import com.ink1804.dev.common.ui.BaseScreen
import com.ink1804.dev.common.ui.BuildConfig
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val googleAuthResultLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.handleGoogleLogin(it.data)
        }

    BaseScreen(
        contentAlignment = Alignment.BottomCenter
    ) {
        val mItems = listOf(
            "Settings",
            "About",
            "Rate app!",
            if (viewModel.getLastSignedInAccount() == null) "Log In" else "Log Out"
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(mItems) {
                ListItem(text = it, onClick = {
                    if (viewModel.getLastSignedInAccount() == null) {
                        val googleSignInClient = viewModel.getGoogleSignInClient()
                        googleAuthResultLauncher.launch(googleSignInClient.signInIntent)
                    } else {
                        viewModel.signOut()
                    }
                })
            }
        }
        Text(
            modifier = Modifier.padding(ProfileScreensDimens.versionTextPadding),
            text = "V ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItem(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
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
            backgroundColor = CardColor,
            onClick = {
                onClick.invoke()
                Toast
                    .makeText(context, " Click on $text", Toast.LENGTH_SHORT)
                    .show()
            }
        ) {
            Text(
                modifier = Modifier.padding(ProfileScreensDimens.listItemTextPadding),
                text = text,
                color = TextColor
            )
        }
    }
}

@Composable
@Preview
fun Preview() {
    ProfileScreen()
}

