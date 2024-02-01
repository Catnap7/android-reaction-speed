package com.main.reactionspeed.screens.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.main.reactionspeed.R
import com.main.reactionspeed.components.ReactionAppBar
import com.main.reactionspeed.components.ShowTierImage
import com.main.reactionspeed.navigation.ReactionSpeedScreens
import com.main.reactionspeed.screens.viewModel.ReactionSpeedViewModel
import com.main.reactionspeed.utils.Tier
import com.main.reactionspeed.utils.formatSeconds
import com.main.reactionspeed.utils.getPercent

@Composable
fun LeaderBoardScreen(
    navController: NavController = NavController(LocalContext.current),
    viewModel: ReactionSpeedViewModel = viewModel()
) {

    Scaffold(
        topBar = {
            ReactionAppBar(
                title = stringResource(id = R.string.leaderboard_title),
                showBackButton = false,
                navController = navController
            )
        },
    ) { it ->
        LeaderBoardContent(
            modifier = Modifier.padding(it),
            navController = navController,
            viewModel
        )
    }
}

@Composable
fun LeaderBoardContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: ReactionSpeedViewModel
) {
    val records = viewModel.getRecordsAvg()
    val tier = Tier.getTier(records)

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "최고기록 : 0.999 초",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(200.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(modifier = Modifier, horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "평균 ${
                                String.format(
                                    "%.3f",
                                    records.formatSeconds()
                                )
                            } 초의 반응속도를 기록했습니다",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "상위 ${getPercent(records)}%",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    ShowTierImage(tier.drawableRes)
                    Text(
                        text = tier.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .height(40.dp),
                onClick = {
                    navController.navigate(ReactionSpeedScreens.HomeScreen.name)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.btn_home),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .height(40.dp),
                onClick = {
                    navController.navigate(ReactionSpeedScreens.SpeedTestScreen.name)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.btn_try_again),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }
}

@Preview
@Composable
fun LeaderBoardScreenPreview() {
    LeaderBoardScreen()
}
