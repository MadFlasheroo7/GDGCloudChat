package xyz.jayeshseth.gdgcloudchat.util.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import xyz.jayeshseth.gdgcloudchat.ui.screens.AuthScreen
import xyz.jayeshseth.gdgcloudchat.ui.screens.ChatScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Destinations.AuthScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = Destinations.AuthScreen.route
        ) {
            AuthScreen(
                navigateToChatScreen = {
                    navController.navigate(Destinations.ChatScreen.route)
                }
            )
        }
        composable(
            route = Destinations.ChatScreen.route
        ) {
            ChatScreen()
        }
    }
}