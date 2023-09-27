package com.codeplace.literalearnsapp.ui.home.view.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.RequestManager
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.codeplace.literalearnsapp.R
import com.codeplace.literalearnsapp.databinding.ActivityHomeBinding
import com.codeplace.literalearnsapp.stateFlow.StateFlow
import com.codeplace.literalearnsapp.ui.home.view.model.DefaulScreenContent
import com.codeplace.literalearnsapp.ui.home.view.model.ShelvesTitles
import com.codeplace.literalearnsapp.ui.home.view.model.ShelvesContent
import com.codeplace.literalearnsapp.ui.home.view.model.ShelvesResults
import com.codeplace.literalearnsapp.ui.home.viewModel.LiteraLearnsViewModel
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private val viewModel by viewModel<LiteraLearnsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        val authCode = intent.getStringExtra("EXTRA_SERVER_AUTH_CODE")
        val personName = intent.getStringExtra("EXTRA_PERSON_NAME")
        val personEmail = intent.getStringExtra("EXTRA_PERSON_EMAIL")

        initValues(authCode)
        initObservables()


        // MessageCard(Messages("Leonardo", "This is the first Composable inside the app"))


//        if (authCode != null){
//
//            initValues(authCode)
//            initObservables()
//
//            with(binding){
//                txtServerAuthCode.text = authCode.toString()
//                txtPersonName.text= personName.toString()
//                txtPersonEmail.text=personEmail.toString()
//            }
//        } else {
//            with(binding){
//                txtServerAuthCode.text = "Server code is null"
//            }
//        }
//
    }


    private fun initValues(authCode:String?) {
        viewModel.getTokenAuthenticated(getString(R.string.server_client_id),getString(R.string.client_secret),authCode,getString(R.string.grant_type),getString(R.string.redirect_uri))
    }
    private fun initObservables() {
        viewModel.tokenAuthenticated.observe(this) {
            when (it) {
                is StateFlow.Loading -> {loading(it.loading)}
                is StateFlow.Success<*> -> viewModel.getBookShelves(it.data as JSONObject)
                is StateFlow.Error -> { errorMessage(it.errorMessage)}
            }
        }
        viewModel.readingNowShelf.observe(this){
            when(it){
                is StateFlow.Loading -> {loading(it.loading)}
                is StateFlow.Success<*>-> viewModel.fillReadingNowShelfList(it.data as JSONObject)
                is StateFlow.Error->{errorMessage(it.errorMessage)}
            }
        }
        viewModel.wantToReadShelf.observe(this){
            when(it){
                is StateFlow.Loading -> {loading(it.loading)}
                is StateFlow.Success<*>-> viewModel.fillWantToReadShelf(it.data as JSONObject)
                is StateFlow.Error->{errorMessage(it.errorMessage)}
            }
        }
        viewModel.readShelf.observe(this){
            when(it){
                is StateFlow.Loading -> {loading(it.loading)}
                is StateFlow.Success<*>-> viewModel.fillReadShelf(it.data as JSONObject)
                is StateFlow.Error->{errorMessage(it.errorMessage)}
            }
        }
        viewModel.allShelvesResult.observe(this){
            initShelvesValues(
                ShelvesResults(
                    coverBookReadingNowList = it.coverBookReadingNowList,
                    coverBookWantToReadList = it.coverBookWantToReadList,
                    coverBookReadList = it.coverBookReadList
                )
            )
        }
    }


    private fun initShelvesValues(shelvesResults: ShelvesResults) {
        setContent {
            val screenTitleMyBooks = getString(R.string.screen_title_my_books)
            val titleReadingNow = getString(R.string.title_reading_now)
            val titleRead = getString(R.string.title_read)
            val titleWantToRead = getString(R.string.title_want_to_read)
            val coverBookDescription = getString(R.string.cover_book_description)

            MyBooksScreen(
                defaultScreenContent = DefaulScreenContent(screenTitleMyBooks, coverBookDescription),
                shelvesTitles = ShelvesTitles(
                    titleReadingNow,
                    titleRead,
                    titleWantToRead
                ), shelvesResults = ShelvesResults(
                    shelvesResults.coverBookReadingNowList,
                    shelvesResults.coverBookWantToReadList,
                    shelvesResults.coverBookReadList)

            )
        }
    }

    private fun errorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
    private fun loading(loading: Boolean) {
        with(binding){
            progressBar.visibility = if (loading) VISIBLE else GONE
        }
    }

}

@Composable
fun MyBooksScreen(defaultScreenContent: DefaulScreenContent,
                  shelvesTitles: ShelvesTitles,
                  shelvesResults: ShelvesResults){
    val contextForToast = LocalContext.current.applicationContext

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(top = 16.dp, start = 6.dp
            ))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                Toast.makeText(contextForToast, "clicked", Toast.LENGTH_SHORT).show()
            }) {
                Image(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier
                        .size(28.dp)

                )
            }
            Text(
                text = defaultScreenContent.title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            IconButton(onClick = { /*TODO*/ }
            ) {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(28.dp)
                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(top = 4.dp, bottom = 16.dp)),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(2.dp)
                    .graphicsLayer(translationY = 2.dp.value / 2)
                    .background(Color.LightGray)
            )

        }
        Column(
            modifier = Modifier
                .padding(paddingValues = PaddingValues(start = 16.dp)),
        )
        {
            Text(
                text = shelvesTitles.readingNowTitle,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ShelvesList(shelvesResults.coverBookReadingNowList, defaultScreenContent)
            }
            Text(
                text = shelvesTitles.wantToReadTitle,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ShelvesList(shelvesResults.coverBookWantToReadList, defaultScreenContent)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
fun LazyListScope.ShelvesList(readingNowList: List<ShelvesContent>,
                                 defaultScreenContent: DefaulScreenContent) {
    items(readingNowList) { coverBookUrl ->

        GlideImage(
                model = coverBookUrl.coverBookUrl,
                contentDescription = defaultScreenContent.coverBooksDescription,
                modifier = Modifier
                    .clickable(onClick = { })
                    .size(height = 220.dp, width = 120.dp)
            )
    }
}

@Preview
@Composable
fun PreviewMyBooksScreen() {
    // MessageCard(msg = Messages("Leonardo","Body test"))
    MyBooksScreen(
        defaultScreenContent = DefaulScreenContent("My Books P", " COVER DESCRIPTION"),
        shelvesTitles = ShelvesTitles("Reading Now T", "Read P", "Want To read P"),
        shelvesResults = ShelvesResults())
}


