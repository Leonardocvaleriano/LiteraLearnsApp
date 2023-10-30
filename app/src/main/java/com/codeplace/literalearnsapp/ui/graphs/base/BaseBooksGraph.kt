package com.codeplace.literalearnsapp.ui.graphs.base

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.core.app.ComponentActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codeplace.literalearnsapp.R
import com.codeplace.literalearnsapp.ui.graphs.BooksNavGraph
import com.codeplace.literalearnsapp.ui.login.viewModel.GoogleSignInViewModel
import com.codeplace.literalearnsapp.ui.main.view.models.MenuItem
import com.codeplace.literalearnsapp.ui.main.view.models.NavBarItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBooksGraph(
    navController: NavHostController = rememberNavController()){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var loginId = "login"
    var loginTitle = "Login"

    val viewModel: GoogleSignInViewModel = koinViewModel()
    val userState by viewModel.userState.collectAsState()


    if (userState.data != null) {
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
            textStyle = TextStyle(fontSize = 14.sp),
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
            route = "search_books",
            title = "Books",
            textStyle = TextStyle(fontSize = 14.sp),
            selectedIcon = painterResource(id = R.drawable.menu_book_filled),
            unselectedIcon = painterResource(id = R.drawable.menu_book_outlined)
        ),
        NavBarItem(
            route = "my_books",
            title = "My Books",
            textStyle = TextStyle(fontSize = 14.sp),
            selectedIcon = painterResource(id = R.drawable.my_books_filled),
            unselectedIcon = painterResource(id = R.drawable.my_books_outlined)
        ),
        NavBarItem(
            route = "learns",
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
            ModalDrawerSheet {

                // Drawer Header
                if (userState.data != null) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(all = 24.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = "User image",
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(shape = CircleShape)
                            )
                            Text(
                                modifier = Modifier
                                    .padding(top = 18.dp, start = 20.dp),
                                text = "${userState.data!!.userName}", fontSize = 26.sp
                            )
                            Text(
                                modifier = Modifier.padding(start = 20.dp, top = 6.dp),
                                text = "${userState.data!!.userEmail}", fontSize = 18.sp
                            )

                        }

                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 64.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(text = "LiteraLearns", fontSize = 58.sp)

                    }

                }
                // Drawer Body
                var selectedDrawerItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }

                drawerItems.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectedDrawerItemIndex,
                        onClick = {
                            selectedDrawerItemIndex = index
                            when (item.id) {
                                "share" -> {}
                                "about" -> {}
                                "login" -> {
                                    viewModel.getSignInIntentSender(launcher)
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
                TopAppBar(
                    title = {
                        Text(text = "App bar name")
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
                    }
                )
            },
            bottomBar = {

                BottomAppBar {
                    NavigationBar {

                        var selectedBottomBarItemIndex by rememberSaveable {
                            mutableStateOf(0)
                        }

                        navBarItems.forEachIndexed { index, navBarItem ->
                            NavigationBarItem(
                                selected = index == selectedBottomBarItemIndex,
                                onClick = {
                                    selectedBottomBarItemIndex = index
                                    navController.navigate(navBarItem.route)
                                },
                                icon = {
                                    Icon(
                                        painter = if (index == selectedBottomBarItemIndex) {
                                            navBarItem.selectedIcon
                                        } else navBarItem.unselectedIcon,
                                        contentDescription = navBarItem.title
                                    )

                                })

                        }

                    }

                }
            }
        ) { paddingValues ->
            BooksNavGraph(navController, paddingValues)
        }
    }
}