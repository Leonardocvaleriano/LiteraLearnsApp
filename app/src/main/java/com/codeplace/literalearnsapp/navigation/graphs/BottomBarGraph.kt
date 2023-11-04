package com.codeplace.literalearnsapp.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeplace.literalearnsapp.navigation.NavBarItem
import com.codeplace.literalearnsapp.navigation.Screen
import com.codeplace.literalearnsapp.ui.screens.LearnsScreen
import com.codeplace.literalearnsapp.ui.screens.MyBooksScreen
import com.codeplace.literalearnsapp.ui.screens.SearchBooksScreen
import com.codeplace.literalearnsapp.ui.viewmodel.AppViewModel

@Composable
fun BottomBarGraph(navController:NavHostController, paddingValues:PaddingValues, viewModelApp: AppViewModel){


    NavHost(
        navController = navController,
        route = Graph.BOTTOM_BAR,
        startDestination = Screen.SearchBooks.route,
        modifier = androidx.compose.ui.Modifier.padding(paddingValues)
    ) {
        composable(Screen.SearchBooks.route) {
            viewModelApp.updateSelectedBottomItemIndex(Screen.SearchBooks.resourceId)
            SearchBooksScreen()

        }
        composable(Screen.MyBooks.route) {
            viewModelApp.updateSelectedBottomItemIndex(Screen.MyBooks.resourceId)
            MyBooksScreen()

        }
        composable(Screen.Learns.route){
            viewModelApp.updateSelectedBottomItemIndex(Screen.Learns.resourceId)
            LearnsScreen()

        }
    }
}