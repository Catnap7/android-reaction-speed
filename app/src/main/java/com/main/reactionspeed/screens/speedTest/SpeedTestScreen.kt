package com.main.reactionspeed.screens.speedTest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.main.reactionspeed.R
import com.main.reactionspeed.components.ReactionAppBar
import com.main.reactionspeed.navigation.ReactionSpeedScreens
import com.main.reactionspeed.screens.viewModel.ReactionSpeedViewModel
import com.main.reactionspeed.utils.formatSeconds
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun SpeedTestScreen(
    navController: NavController = NavController(LocalContext.current),
    viewModel: ReactionSpeedViewModel = viewModel()
) {

    Scaffold(
        topBar = {
            ReactionAppBar(
                title = "",
                showBackButton = true,
                navController = navController
            )
        }
    ) {
        SpeedTestScreenContent(
            modifier = Modifier.padding(it),
            navController = navController,
            viewModel = viewModel
        )
    }
}

@Composable
fun SpeedTestScreenContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ReactionSpeedViewModel
) {

    val count = remember { mutableIntStateOf(1) }
    val timerCount = remember { mutableStateOf(3) }
    val elapsedTime = remember { mutableStateOf(0) }
    var firstRecord by remember { mutableStateOf(0) }
    var secondRecord by remember { mutableStateOf(0) }
    var thirdRecord by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = count.intValue) {
        for (i in 3 downTo 0) {
            timerCount.value = i
            delay(1000)
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "${count.intValue}/3")
            when (count.intValue) {
                2 -> {
                    firstRecord = elapsedTime.value
                    Text("1차 시도 : ${elapsedTime.value.formatSeconds()} 초")
                }

                3 -> {
                    secondRecord = elapsedTime.value
                    Text("2차 시도 : ${elapsedTime.value.formatSeconds()} 초")
                }

                4 -> {
                    viewModel.updateRecordUiState(firstRecord, secondRecord, elapsedTime.value)
                    navController.navigate(ReactionSpeedScreens.LeaderBoardScreen.name)
                }
            }
            Spacer(modifier = Modifier.height(100.dp))

            if (timerCount.value > 0) {
                Box(modifier = Modifier.size(300.dp), contentAlignment = Alignment.Center) {
                    Text(
                        text = "${timerCount.value}",
                        style = MaterialTheme.typography.displayLarge
                    )
                }

            } else if (timerCount.value == 0) {
                RandomColorButton(
                    navController,
                    count = count,
                    elapsedTime = elapsedTime,
                    timerCount = timerCount
                )
            }
        }
    }
}

@Composable
fun RandomColorButton(
    navController: NavController,
    count: MutableState<Int>,
    elapsedTime: MutableState<Int>,
    timerCount: MutableState<Int>
) {
    val clickText = stringResource(id = R.string.click_me)
    val readyText = stringResource(id = R.string.get_ready)
    val getReadyColor = colorResource(id = R.color.btn_get_ready)
    val greenColor = colorResource(id = R.color.btn_click_me)
    var randomColor by remember { mutableStateOf(getReadyColor) }
    var buttonText by remember { mutableStateOf(readyText) }
    var isAnimating by remember { mutableStateOf(false) }
    var greenColorTimestamp by remember { mutableStateOf(0L) }
    val delay = Random.nextLong(3000, 6000)

    LaunchedEffect(key1 = randomColor) {
        isAnimating = true
        delay(delay)
        greenColorTimestamp = System.currentTimeMillis()
        randomColor = greenColor
        buttonText = clickText
        isAnimating = false
    }

    Button(
        modifier = Modifier
            .size(300.dp),
        onClick = {
            if (randomColor == greenColor) {
                elapsedTime.value = (System.currentTimeMillis() - greenColorTimestamp).toInt()
                count.value++
                buttonText = readyText
                timerCount.value = 3
            }

            randomColor = getReadyColor
        },
        colors = ButtonDefaults.buttonColors(containerColor = randomColor)
    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Preview
@Composable
fun SpeedTestScreenPreview() {
    SpeedTestScreen()
}