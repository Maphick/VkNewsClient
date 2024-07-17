package com.makashovadev.vknewsclient.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.makashovadev.vknewsclient.domain.FeedPost
import com.makashovadev.vknewsclient.navigation.AppNavGraph
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import com.makashovadev.vknewsclient.navigation.rememberNavigationState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(bottomBar = {
        NavigationBar {
            // текущий открытый экран
            val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
            //  текущий экран
            val items = listOf(
                NavigationItem.Home, NavigationItem.Favourite, NavigationItem.Profile
            )
            items.forEachIndexed { index, item ->
                val selected = navBackStackEntry?.destination?.hierarchy?.any {
                    it.route == item.screen.route
                } ?: false

                NavigationBarItem(selected = selected,
                    onClick = {
                        if (!selected) {
                            navigationState.navigateTo(item.screen.route)
                        }
                    },
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(text = stringResource(id = item.titleResId)) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                        indicatorColor = MaterialTheme.colorScheme.onSecondary
                    )
                )
            }
        }
    }) { paddingValues ->
        AppNavGraph(navHostController = navigationState.navHostController,
            newsFeedScreenContent = {
                HomeScreen(paddingValues = paddingValues, onCommentClickListener = {
                    commentsToPost.value = it
                    navigationState.navigateToComments()
                })
            },
            commentsScreenContent = {
                CommentsScreen(
                    onBackPressed = {
                        //  интуитивное поведение приложения:
                        // если пользователь кликает назад - закрыть данный экран
                        navigationState.navHostController.popBackStack()
                    }, feedPost = commentsToPost.value!!
                )
            },
            favouriteScreenContent = { TextCounter(name = "Favourite") },
            profileScreenContent = { TextCounter(name = "Profile") })
    }
}


@Composable
fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .padding(
                top = 150.dp
            )
            .fillMaxSize()
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    count++
                },
            text = "${name} Count: ${count}",
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = Color.Black
        )
    }
}



