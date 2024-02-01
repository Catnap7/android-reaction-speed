@file:OptIn(ExperimentalMaterial3Api::class)

package com.main.reactionspeed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.main.reactionspeed.navigation.ReactionSpeedScreens

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ReactionAppBar(
    title: String = "반응 속도 테스트",
    showBackButton: Boolean = false,
    navController: NavController = NavController(context = LocalContext.current),
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            when (showBackButton) {
                true -> {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }

                false -> {
                    IconButton(onClick = { navController.navigate(ReactionSpeedScreens.HomeScreen.name) }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                }
            }

        },
    )
}

@Composable
fun ShowTierImage(drawableRes: Int) {
    Image(
        painter = painterResource(drawableRes),
        contentDescription = "Tier Image",
        modifier = Modifier
            .height(100.dp)
    )


}
