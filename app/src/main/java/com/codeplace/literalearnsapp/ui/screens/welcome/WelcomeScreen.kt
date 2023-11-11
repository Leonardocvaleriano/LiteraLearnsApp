package com.codeplace.literalearnsapp.ui.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codeplace.literalearnsapp.navigation.Screen
import com.codeplace.literalearnsapp.ui.util.OnBoardingPage
import com.codeplace.literalearnsapp.ui.viewmodel.GoogleSignInViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavController
) {



    val viewModel: GoogleSignInViewModel = koinViewModel()

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
                navController.navigate(route = Screen.SearchBooks.route)
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





