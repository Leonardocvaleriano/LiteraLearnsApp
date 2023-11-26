package com.codeplace.literalearnsapp.presentation.welcome

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import androidx.navigation.NavController
import com.codeplace.literalearnsapp.presentation.navigation.util.Screen
import com.codeplace.literalearnsapp.util.OnBoardingPage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavController,
//    state: SignInState
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult() ,
        onResult = {result ->
            if (result.resultCode == ComponentActivity.RESULT_OK){
            // integrate onEvent (maybe)
            //viewModel.getLauncherForActivityResult(result = result)
            }
        }
    )
//
//    LaunchedEffect(key1 = state.isSignInSuccessful){
//        if (state.isSignInSuccessful){
//            navController.popBackStack()
//            navController.navigate(Screen.SearchBooks.route)
//        }
//
//    }

    val pages = listOf(
        OnBoardingPage.FirstPage,
        OnBoardingPage.SecondPage,
        OnBoardingPage.ThirdPage,
        OnBoardingPage.FourthPage
    )

    val pagesSize = pages.size
    val lastPage = pages.lastIndex
    val pagerState = rememberPagerState(initialPage = 0) { pagesSize }

    val textColorsApp = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary

    Column(

        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
        //.background(color = color)
    ) {

        SkipClickableText(
            pagerState = pagerState,
            lastPage = lastPage,
            onClick = {
                navController.popBackStack()
                navController.navigate(route = Screen.Books.route)
            }
        )
        HorizontalPager(
            state = pagerState,
        ) { position ->
            PagerScreen(pages[position])
        }
        SignInButton(
            lastPage = lastPage,
            pagerState = pagerState
        ) {
            //viewModel.signIn(launcher = launcher)

        }
        HorizontalPagerIndicator(pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SkipClickableText(
    lastPage: Int,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = pagerState.currentPage == lastPage
        ) {
            Text(
                modifier = Modifier.clickable(onClick = onClick),
                text = "Skip",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "Pager image",
            contentScale = ContentScale.Inside
        )
        Text(
            text = onBoardingPage.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            text = onBoardingPage.description,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignInButton(
    lastPage: Int,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = pagerState.currentPage == lastPage
    ) {

        Button(
            onClick = onClick) {
            Text(text = "Sign in")
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerIndicator(pagerState: PagerState) {

    Row(
        Modifier
            .padding(bottom = 16.dp)
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(pagerState.pageCount) { iteration ->

            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(12.dp)
            )
        }
    }
}


@Composable
fun FirstOnBoardingScreen(onBoardingPage: OnBoardingPage) {


}

@Composable
fun SecondOnBoardingScreen() {

}

@Composable
fun ThirdOnBoardingScreen() {

}

@Composable
fun FourthOnBoardingScreen() {
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    PagerScreen(onBoardingPage = OnBoardingPage.FirstPage)
}

@Composable
@Preview(showBackground = true)
fun SecondOnBoardingScreenPreview() {
    PagerScreen(onBoardingPage = OnBoardingPage.SecondPage)
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview() {
    PagerScreen(onBoardingPage = OnBoardingPage.ThirdPage)
}


@Composable
@Preview(showBackground = true)
fun FourthOnBoardingScreenPreview() {
    PagerScreen(onBoardingPage = OnBoardingPage.FourthPage)
}





