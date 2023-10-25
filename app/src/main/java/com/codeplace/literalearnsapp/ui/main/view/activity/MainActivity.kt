package com.codeplace.literalearnsapp.ui.main.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.codeplace.literalearnsapp.ui.graphs.RootNavGraph
import com.codeplace.literalearnsapp.ui.login.view.activity.theme.LiteraLearnsAppTheme

class MainActivity : ComponentActivity() {

    val userFirstAccess: Boolean = false
    var firstPage: String = ""

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentlyStartDestination = "welcome"

        setContent {
            LiteraLearnsAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    RootNavGraph(
                        navController = navController,
                        currentlyStartDestination = currentlyStartDestination)

                }
            }
        }
    }
}





