package com.codeplace.literalearnsapp.ui.graphs

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeplace.literalearnsapp.ui.login.viewModel.GoogleSignInViewModel
import com.codeplace.literalearnsapp.ui.main.view.screens.SearchBookScreen
import com.codeplace.literalearnsapp.ui.welcome.screens.WelcomeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun RootNavGraph(
    navController: NavHostController,
    currentlyStartDestination: String,

){
    NavHost(
        navController = navController,
        startDestination = currentlyStartDestination,
        route = Graph.ROOT){

        composable(route = "welcome"){

            val viewModel:GoogleSignInViewModel = koinViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == ComponentActivity.RESULT_OK){
                            viewModel.getLauncherForActivityResult(result)
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful){
                if (state.isSignInSuccessful){
//                    Toast.makeText(
//                        applicationContext,
//                        "Sign in successful",
//                        Toast.LENGTH_LONG
//                    ).show()

                    navController.popBackStack()
                    navController.navigate("SearchBook")
                    viewModel.resetState()
                }
            }

            WelcomeScreen(
                state = state,
                onSignInClick = {
                    viewModel.getSignInIntentSender(launcher = launcher)
                }
            )
        }

        composable("SearchBook"){
            SearchBookScreen()
        }
    }

}


object Graph{
    const val ROOT = "root_graph"
    const val MY_BOOKS = "my_books_graph"
}