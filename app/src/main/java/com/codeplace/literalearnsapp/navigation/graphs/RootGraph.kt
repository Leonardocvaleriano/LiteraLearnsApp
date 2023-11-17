package com.codeplace.literalearnsapp.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeplace.literalearnsapp.navigation.Screen
import com.codeplace.literalearnsapp.presentation.appentrynavigation.AppEntryNavigation
import com.codeplace.literalearnsapp.presentation.welcome.WelcomeScreen

@Composable
fun RootNavGraph(
    currentStartDestination: String,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = currentStartDestination,
        route = Graph.ROOT
    ) {

        composable("welcome"){
            WelcomeScreen(navController = navController)

        }

        composable(Screen.SearchBooks.route){

            AppEntryNavigation()
        }
    }

}

object Graph {
    const val ROOT = "root"
    const val BOTTOM_BAR = "bottom_bar"
}