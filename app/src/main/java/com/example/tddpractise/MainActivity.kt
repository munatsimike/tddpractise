package com.example.tddpractise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tddpractise.miscellaneous.navigation.AppNavGraph
import com.example.tddpractise.miscellaneous.navigation.destination
import com.example.tddpractise.ui.theme.TDDPractiseTheme
import com.example.tddpractise.ui.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDDPractiseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color.Black
                ) {
                    MyScaffold()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(modifier: Modifier = Modifier) {
    val userViewModel:UserViewModel = hiltViewModel()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val routes = listOf(destination.Home, destination.Detail)

    Scaffold(modifier = Modifier, topBar = {
        TopAppBar(title = { Text(text = "Hopeless") }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black, titleContentColor = Color.White
        ), navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "",
                tint = Color.White
            )
        })
    }, bottomBar = {
        BottomAppBar(


        ) {
            routes.forEach {
                NavigationBarItem(
                    selected = currentRoute == it.route,
                    onClick = {
                        navController.navigate(it.route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },

                    icon = { it.icon },
                    label = { Text(text = it.destination) }
                )
            }
        }
    }, content = { paddingValues ->
        Box {
            modifier.padding(paddingValues)
            val users = userViewModel.userStateFlow.collectAsState()
            AppNavGraph(navController, users, userViewModel)
        }
    })
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL,
    backgroundColor = 0xFFE91E63,
    widthDp = 400,
    heightDp = 100
)
@Composable
fun DefaultPreview() {
    TDDPractiseTheme {
        MyScaffold()
    }
}