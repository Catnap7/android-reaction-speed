package com.main.reactionspeed.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.main.reactionspeed.components.ReactionAppBar
import com.main.reactionspeed.navigation.ReactionSpeedScreens
import com.main.reactionspeed.screens.viewModel.ReactionSpeedViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: ReactionSpeedViewModel) {

    Scaffold(
        topBar = {
            ReactionAppBar(
                title = "반응 속도 테스트",
                showBackButton = false,
                navController = navController
            )
        }
    ) {
        HomeScreenContent(navController)
    }
}

@Preview
@Composable
fun HomeScreenContent(navController: NavController = NavController(LocalContext.current)) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "버튼이 초록색으로 바뀌면 터치하세요")
            Text(text = "3번의 기회가 주어지며 3초 뒤 시작합니다")

            Spacer(modifier = Modifier.height(10.dp))
            Row() {
                Button(
                    shape = MaterialTheme.shapes.extraLarge,
                    onClick = {
                        navController.navigate(ReactionSpeedScreens.SpeedTestScreen.name)
                    }) {
                    Text(text = "시작")
                }
            }
        }
    }
}