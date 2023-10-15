package com.codeplace.literalearnsapp.ui.main.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codeplace.literalearnsapp.remote.GoogleAuthUiClient
import com.codeplace.literalearnsapp.ui.home.view.screens.HomeScreen
import com.codeplace.literalearnsapp.ui.introduction.view.activity.screens.IntroductionScreens
import com.codeplace.literalearnsapp.ui.introduction.view.activity.theme.LiteraLearnsAppTheme
import com.codeplace.literalearnsapp.ui.login.viewModel.GoogleSignInViewModel
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val userFirstAccess:Boolean = false
    var firstPage:String = ""

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext))
    }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiteraLearnsAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


//                    if(userFirstAccess) {
//                        firstPage = "introduction"
//
//                    } else {
//                        firstPage = "home"
//                    }

                    val navControler = rememberNavController()
                    NavHost(navController = navControler, startDestination = "introduction"){

                        composable("introduction"){

                            val viewModel = viewModel<GoogleSignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK){
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSignInSuccessful){
                                if (state.isSignInSuccessful){
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in successful",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navControler.navigate("home")
                                    viewModel.resetState()
                                }
                            }

                            IntroductionScreens(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                }
                            )
                        }

                        composable("home"){

                            var isUserLogged = false
                            LaunchedEffect(key1 = Unit ){
                                if (googleAuthUiClient.getSignedInUser() != null){
                                    isUserLogged = true
                                }
                            }
                            HomeScreen(isUserLogged = isUserLogged , navController = navControler)

                        }
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LiteraLearnsAppTheme {
        //DrawerHeader(isUserLogged = true)
    }
}

//@Composable
//fun HomeScreen(){
//        Scaffold {
//
//        }
//    }


