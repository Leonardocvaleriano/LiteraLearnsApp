package com.codeplace.literalearnsapp.presentation.appentrynavigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.codeplace.literalearnsapp.R
import com.codeplace.literalearnsapp.navigation.graphs.BottomBarGraph
import com.codeplace.literalearnsapp.MainActivity.*
import com.codeplace.literalearnsapp.navigation.MenuItem
import com.codeplace.literalearnsapp.navigation.NavBarItem
import com.codeplace.literalearnsapp.navigation.Screen
import com.codeplace.literalearnsapp.presentation.GoogleSignInViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppEntryNavigation(navController: NavHostController = rememberNavController()) {


    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var loginId = "login"
    var loginTitle = "Login with Google"

    val viewModel: GoogleSignInViewModel = koinViewModel()
    val viewModelApp = viewModel<AppNavigationViewModel>()

    val userDataState by viewModel.userDataState.collectAsState()

    val selectedBottomBarItemIndex = viewModelApp.selectedBottomItemIndex

    if (userDataState.data != null) {
        loginId = "logout"
        loginTitle = "Logout"
    }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                viewModel.getLauncherForActivityResult(result)
            }
        }
    )

    // Items inside the drawer as list.
    val drawerItems = listOf(
        MenuItem(
            id = "share",
            title = "Share",
            contentDescription = "Share",
            textStyle = TextStyle(
                fontSize = 14.sp
            ),
            selectedIcon = Icons.Filled.Share,
            unselectedIcon = Icons.Outlined.Share

        ),
        MenuItem(
            id = "about",
            title = "About",
            contentDescription = "About",
            textStyle = TextStyle(fontSize = 14.sp),
            selectedIcon = Icons.Default.Info,
            unselectedIcon = Icons.Outlined.Info
        ),
        MenuItem(
            id = loginId,
            title = loginTitle,
            description = "Login in to share your books across devices",
            contentDescription = "sign in with google",
            textStyle = TextStyle(fontSize = 14.sp),
            selectedIcon = Icons.Default.ExitToApp,
            unselectedIcon = Icons.Outlined.ExitToApp
        )

    )


    val navBarItems = listOf(
        NavBarItem(
            route = Screen.SearchBooks.route,
            title = "Books",
            textStyle = TextStyle(fontSize = 14.sp),
            selectedIcon = painterResource(id = R.drawable.menu_book_filled),
            unselectedIcon = painterResource(id = R.drawable.menu_book_outlined)
        ),
        NavBarItem(
            route = Screen.MyBooks.route,
            title = "My Books",
            textStyle = TextStyle(fontSize = 14.sp),
            selectedIcon = painterResource(id = R.drawable.my_books_filled),
            unselectedIcon = painterResource(id = R.drawable.my_books_outlined)
        ),
        NavBarItem(
            route = Screen.Learns.route,
            title = "Learns",
            textStyle = TextStyle(fontSize = 14.sp),
            selectedIcon = painterResource(id = R.drawable.learns_filled),
            unselectedIcon = painterResource(id = R.drawable.learns_outlined)
        )

    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Drawer contents
            ModalDrawerSheet(
                modifier = Modifier
                    .width(300.dp)
            ) {

                val color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
                // Drawer Header
                if (userDataState.data != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 14.dp, end = 14.dp)
                        ) {
                            AsyncImage(
                                model = userDataState.data!!.profilePictureUrl,
                                contentDescription = "Profile picture",
                                modifier = Modifier
                                    .padding(top = 32.dp)
                                    .size(150.dp)
                                    .clip(shape = CircleShape)
                                    .border(shape = CircleShape, border = BorderStroke(3.dp, color))
                            )
                            Text(
                                modifier = Modifier
                                    .padding(top = 32.dp),
                                text = "${userDataState.data!!.userName}",
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 24.sp,

                            )
                            Text(
                                text = "${userDataState.data!!.userEmail}",
                                style = MaterialTheme.typography.titleSmall,
                                fontSize = 13.sp
                            )

                        }

                    }
                } else {
                        Column(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {
                            Text(
                                text = "LiteraLearns",
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 32.sp,
                            )

                    }
                }
                Divider(modifier = Modifier.padding(top = 32.dp, bottom = 32.dp))

                // Drawer Body
                var selectedDrawerItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }

                drawerItems.forEachIndexed { index, item ->

                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = false,
                        onClick = {
                            selectedDrawerItemIndex = index
                            when (item.id) {
                                "share" -> {}
                                "about" -> {}
                                "login" -> {
                                    viewModel.signIn(launcher)
                                }

                                "logout" -> {
                                    viewModel.singOut()
                                    viewModel.resetUserDataState()
                                }
                            }
                        },


                        icon = {
                            Icon(
                                imageVector = if (index == selectedDrawerItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.contentDescription
                            )
                        }
                    )
                }
            }
        },
        gesturesEnabled = drawerState.isOpen
    ) {


        Scaffold(
            topBar = {

                val titleSelected = navBarItems[selectedBottomBarItemIndex].title

                CenterAlignedTopAppBar(
                    title = {
                        Text(text = titleSelected)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                viewModel.getSignedInUser()
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }

                            }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )

                        }
                    }
                )

            },
            bottomBar = {

                BottomAppBar {
                    NavigationBar {

                        navBarItems.forEachIndexed { index, navBarItem ->
                            NavigationBarItem(
                                selected = index == selectedBottomBarItemIndex,
                                onClick = {
                                    navController.navigate(navBarItem.route)
                                },
                                icon = {
                                    Icon(
                                        painter = if (index == selectedBottomBarItemIndex) {
                                            navBarItem.selectedIcon
                                        } else navBarItem.unselectedIcon,
                                        contentDescription = navBarItem.title,

                                        )

                                }, label = {
                                    Text(
                                        text = navBarItem.title
                                    )
                                })

                        }

                    }

                }
            }
        ) { paddingValues ->
            BottomBarGraph(
                navController = navController,
                paddingValues = paddingValues,
                viewModelApp
            )
        }
    }
}


