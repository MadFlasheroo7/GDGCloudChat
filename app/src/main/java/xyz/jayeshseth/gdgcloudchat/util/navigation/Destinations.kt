package xyz.jayeshseth.gdgcloudchat.util.navigation

import xyz.jayeshseth.gdgcloudchat.util.Constants.AUTH_SCREEN
import xyz.jayeshseth.gdgcloudchat.util.Constants.CHAT_SCREEN

sealed class Destinations(val route: String) {
    object AuthScreen: Destinations(AUTH_SCREEN)
    object ChatScreen: Destinations(CHAT_SCREEN)
}
