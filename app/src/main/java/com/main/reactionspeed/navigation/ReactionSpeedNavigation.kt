package com.main.reactionspeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.main.reactionspeed.screens.home.HomeScreen
import com.main.reactionspeed.screens.leaderboard.LeaderBoardScreen
import com.main.reactionspeed.screens.speedTest.SpeedTestScreen
import com.main.reactionspeed.screens.viewModel.ReactionSpeedViewModel

@Composable
fun ReactionSpeedNavigation() {
    val navController = rememberNavController()
//    val reactionSpeedViewModel : ReactionSpeedViewModel = navController.navGraphViewModel()
    val reactionSpeedViewModel = ReactionSpeedViewModel()

    NavHost(navController = navController, startDestination = ReactionSpeedScreens.HomeScreen.name) {
        composable(ReactionSpeedScreens.HomeScreen.name) {
            HomeScreen(navController = navController, viewModel = reactionSpeedViewModel)
        }
        composable(ReactionSpeedScreens.SpeedTestScreen.name) {
            SpeedTestScreen(navController = navController, viewModel = reactionSpeedViewModel)
        }
        composable(ReactionSpeedScreens.LeaderBoardScreen.name) {
            LeaderBoardScreen(navController = navController, viewModel = reactionSpeedViewModel)
        }
    }


}