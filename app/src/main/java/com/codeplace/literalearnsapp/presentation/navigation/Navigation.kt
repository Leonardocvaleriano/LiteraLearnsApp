package com.codeplace.literalearnsapp.presentation.navigation

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
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.codeplace.literalearnsapp.MainActivity.*
import com.codeplace.literalearnsapp.R
import com.codeplace.literalearnsapp.presentation.navigation.graphs.BottomBarGraph
import com.codeplace.literalearnsapp.presentation.navigation.util.DrawerItem
import com.codeplace.literalearnsapp.presentation.navigation.util.Screen
import com.codeplace.literalearnsapp.presentation.googlesignIn.SignInState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    signInState: SignInState,
    navController2: NavHostController = rememberNavController()
) {

//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartIntentSenderForResult(),
//        onResult = { result ->
//            if (result.resultCode == ComponentActivity.RESULT_OK) {
//                onEvent.
//                viewModel.getLauncherForActivityResult(result)
//            }
//        }
//    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val screens = listOf(
        Screen.Books,
        Screen.MyBooks,
        Screen.Learns
    )

    val navBackStackEntry by navController2.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

//    if (signInState.data != null) {
//        loginId = "logout"
//        loginTitle = "Logout"
//    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Drawer contents
            ModalDrawerSheet(
                modifier = Modifier
                    .width(300.dp)
            ) {
                ModalDrawerContent(signInState = signInState)
            }
        },
        gesturesEnabled = drawerState.isOpen
    ) {
        Scaffold(
            topBar = {
                //val bottomBarDestination = screens.any { it.route == currentDestination?.route }
                val topAppBarTitle = when (currentRoute) {
                    Screen.Books.route -> Screen.Books.title
                    Screen.MyBooks.route -> Screen.MyBooks.title
                    Screen.Learns.route -> Screen.Learns.title
                    else -> ""
                }
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = topAppBarTitle!!)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                //viewModel.getSignedInUser()
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )

                        }
                    },
                    actions = {
                        IconButton(onClick = {/*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigation {
                    BottomBarContent(
                        navController2 = navController2,
                        items = screens,
                        currentDestination = currentDestination
                    )
                }
            }


        ) { paddingValues ->
            BottomBarGraph(
                paddingValues = paddingValues,
                navController2 = navController2
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawerContent(
    signInState: SignInState
) {

    var loginId = "login"
    var loginTitle = "Login with Google"


    val color =
        if (isSystemInDarkTheme()) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary

    // Drawer Header
    if (signInState.data != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp)
            ) {
                AsyncImage(
                    model = signInState.data.profilePictureUrl,
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
                    text = "${signInState.data.userName}",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 24.sp,

                    )
                Text(
                    text = "${signInState.data.userEmail}",
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
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "LiteraLearns",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 32.sp,
            )

        }
    }
    Divider(modifier = Modifier.padding(top = 32.dp, bottom = 32.dp))

    val drawerItems = listOf(
        DrawerItem.Share,
        DrawerItem.About,
        DrawerItem.Login,
        DrawerItem.Logout
    )

    // Drawer Body
    drawerItems.forEach { item ->
        NavigationDrawerItem(
            label = { Text(text = item.title) },
            selected = false,
            onClick = {
                when (item.resourceId) {
                    R.string.share -> {}
                    R.string.about -> {}
                    R.string.login -> {}
                    R.string.logout -> {}
                }
            },

            icon = {
                Icon(
                    painter = painterResource(id = item.iconPainter!!),
                    contentDescription = item.contentDescription
                )
            }

        )
    }
}

@Composable
fun BottomBarContent(
    navController2: NavHostController,
    items: List<Screen>,
    currentDestination: NavDestination?
) {

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {

        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController2.navigate(screen.route)
                    {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
//                        popUpTo(navController2.graph.findStartDestination().id) {
//                            saveState = true
//                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                            painterResource(id = screen.selectedIcon!!)
                        } else {
                            painterResource(id = screen.unselectedIcon!!)
                        },
                        contentDescription = screen.contentDescription
                    )
                },
                label = {
                    //Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        style = MaterialTheme.typography.labelSmall,
                        text = screen.title!!
                    )
                }
            )
        }
    }
}

