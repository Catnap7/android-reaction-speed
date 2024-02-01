package com.main.reactionspeed.navigation

enum class ReactionSpeedScreens {
    HomeScreen,
    SpeedTestScreen,
    LeaderBoardScreen;

    companion object {
        fun fromRoute(route: String?): ReactionSpeedScreens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            SpeedTestScreen.name -> SpeedTestScreen
            LeaderBoardScreen.name -> LeaderBoardScreen
            null -> HomeScreen
            else -> HomeScreen
        }
    }
}