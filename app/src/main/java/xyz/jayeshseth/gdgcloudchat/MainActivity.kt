package xyz.jayeshseth.gdgcloudchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import xyz.jayeshseth.gdgcloudchat.ui.screens.ChatScreen
import xyz.jayeshseth.gdgcloudchat.ui.theme.GDGCloudChatTheme
import xyz.jayeshseth.gdgcloudchat.util.model.AuthViewModel
import xyz.jayeshseth.gdgcloudchat.util.navigation.Destinations
import xyz.jayeshseth.gdgcloudchat.util.navigation.NavGraph

@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GDGCloudChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberAnimatedNavController()
                    NavGraph(
                        navController = navController
                    )
                    CheckAuthState()
                }
            }
        }
    }

    @Composable
    fun CheckAuthState() {
        if (viewModel.isUserAuthenticated) navigateToChatScreen()
    }

    private fun navigateToChatScreen() = navController.navigate(Destinations.ChatScreen.route)
}
