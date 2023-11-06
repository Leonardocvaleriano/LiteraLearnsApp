package com.codeplace.literalearnsapp.ui.screens.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codeplace.literalearnsapp.ui.util.OnBoardingPage


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navController:NavController
) {

    val pages = listOf(
        OnBoardingPage.FirstPage,
        OnBoardingPage.SecondPage,
        OnBoardingPage.ThirdPage,
        OnBoardingPage.FourthPage
    )

    val pageCount = pages.size
    val pagerState = rememberPagerState(initialPage = 0) { pageCount }


    HorizontalPager(state = pagerState) { position ->
        val color = if (isSystemInDarkTheme()) Color.Blue else Color.White
        //Text(text = "Page: $page")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = color)
                .padding(top = 400.dp)
        ) {
            PagerScreen(pages[position])
        }
    }

    // page indicator
    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(PaddingValues(bottom = 6.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(20.dp)
            )
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top){
      Image(
          painter = painterResource(id = onBoardingPage.image),
          contentDescription = "Pager image"
      )

    }

}


@Composable
fun FirstOnBoardingScreen(onBoardingPage: OnBoardingPage){


}

@Composable
fun SecondOnBoardingScreen(){

}

@Composable
fun ThirdOnBoardingScreen(){

}

@Composable
fun FourthOnBoardingScreen(){
}


@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview(){
    PagerScreen(onBoardingPage = OnBoardingPage.FirstPage)
}

@Composable
@Preview(showBackground = true)
fun SecondOnBoardingScreenPreview(){
    SecondOnBoardingScreen()
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview(){
    ThirdOnBoardingScreen()
}


@Composable
@Preview(showBackground = true)
fun FourthOnBoardingScreenPreview(){
    FourthOnBoardingScreen()
}





