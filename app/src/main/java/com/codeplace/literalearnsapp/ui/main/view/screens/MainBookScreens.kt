package com.codeplace.literalearnsapp.ui.main.view.screens

import TopAppBarWithDrawer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun MainBookScreens(
    navController: NavController = rememberNavController()
) {
    TopAppBarWithDrawer(navController = navController)

}



