package com.codeplace.literalearnsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codeplace.literalearnsapp.navigation.graphs.RootNavGraph
import com.codeplace.literalearnsapp.ui.theme.LiteraLearnsAppTheme


class MainActivity : ComponentActivity() {

    val userFirstAccess: Boolean = false
    var firstPage: String = ""

    private lateinit var navController: NavHostController


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentStartDestination = "welcome"

        setContent {
            LiteraLearnsAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    RootNavGraph(currentStartDestination, navController = navController)

                }

            }
        }
    }
}






