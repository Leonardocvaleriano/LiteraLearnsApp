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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
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
import androidx.navigation.NavController
import com.codeplace.literalearnsapp.R
import com.codeplace.literalearnsapp.ui.graphs.BooksNavGraph
import com.codeplace.literalearnsapp.ui.login.viewModel.GoogleSignInViewModel
import com.codeplace.literalearnsapp.ui.main.view.models.MenuItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithDrawer(navController: NavController) {
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

    // Items inside the drawer as list.
    val items = listOf(
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
                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }

                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            when (item.id) {
                                "share" -> {}
                                "about" -> {}
                                "login" -> {

                                }
                                "logout" -> {
                                    viewModel.singOut()
                                    viewModel.resetUserDataState()

                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
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
        }
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
            }
        ) { paddingValues ->
            BooksNavGraph(navController = navController, paddingValues)
        }
    }
}