package com.example.tddpractise.miscellaneous.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tddpractise.miscellaneous.state.NetworkState
import com.example.tddpractise.ui.screens.DisplayAddUserScreen
import com.example.tddpractise.ui.screens.DisplayUsersScreen
import com.example.tddpractise.ui.viewModel.UserViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    users: State<NetworkState>,
    userViewModel: UserViewModel
) {

    NavHost(navController = navController, startDestination = destination.Home.route) {
        composable(
            route = destination.Home.route
        ) {
            DisplayAddUserScreen(userViewModel)
        }

        composable(
            route = destination.Detail.route
        ) {
            DisplayUsersScreen(users)
        }
    }
}