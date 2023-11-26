package com.codeplace.literalearnsapp.presentation.navigation.graphs

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeplace.literalearnsapp.presentation.navigation.Navigation
import com.codeplace.literalearnsapp.presentation.navigation.util.Screen
import com.codeplace.literalearnsapp.presentation.googlesignIn.GoogleSignInViewModel
import com.codeplace.literalearnsapp.presentation.welcome.WelcomeScreen

@Composable
fun RootNavGraph(
    currentStartDestination: String,
    navController1: NavHostController

) {
    NavHost(
        navController = navController1,
        startDestination = currentStartDestination.toString(),
        route = Graph.ROOT
    ) {

        composable("welcome"){

//            val googleViewModel = hiltViewModel<GoogleSignInViewModel>()
//            val singInState = googleViewModel.state

            WelcomeScreen(
                navController = navController1)
        }

        composable(Screen.Books.route){

            val googleViewModel = hiltViewModel<GoogleSignInViewModel>()
            val singInState = googleViewModel.state

            Navigation(
                signInState = singInState
            )
        }
    }

}

object Graph {
    const val ROOT = "root"
    const val BOTTOM_BAR = "bottom_bar"
}