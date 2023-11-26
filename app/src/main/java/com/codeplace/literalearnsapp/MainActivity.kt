package com.codeplace.literalearnsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codeplace.literalearnsapp.presentation.navigation.util.Screen
import com.codeplace.literalearnsapp.presentation.navigation.graphs.RootNavGraph
import com.codeplace.literalearnsapp.presentation.googlesignIn.GoogleSignInViewModel
import com.codeplace.literalearnsapp.presentation.splash.SplashScreenViewModel
import com.codeplace.literalearnsapp.presentation.ui.theme.LiteraLearnsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val userFirstAccess: Boolean = false
    var firstPage: String = ""

    private lateinit var navController1: NavHostController
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = SplashScreenViewModel()
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { viewModel.isLoading.value }

        val currentStartDestination = Screen.Welcome.route

        setContent {
            LiteraLearnsAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    val googleViewModel = hiltViewModel<GoogleSignInViewModel>()
                    val singInState = googleViewModel.state

                    navController1 = rememberNavController()
                    RootNavGraph(
                        currentStartDestination = currentStartDestination,
                        navController1 = navController1,
                    )

                }
            }
        }
    }
}






