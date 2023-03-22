package com.example.tddpractise.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tddpractise.miscellaneous.state.NetworkState
import com.example.tddpractise.ui.viewModel.UserViewModel

@Composable
fun DisplayUsersScreen(
   users: State<NetworkState>,  modifier: Modifier = Modifier
) {

    LazyColumn(
        content = {
            when (val state = users.value) {
                is NetworkState.Loading -> {

                }
                is NetworkState.Success -> {
                    items(state.users) { user ->
                        Row {
                            Text(text = user.name)
                            Spacer(modifier = modifier.width(2.dp))
                            Text(text = user.surname)
                        }
                    }
                }
                is NetworkState.NotLoading -> {

                }
                else -> {}
            }

        }, modifier = modifier.testTag("users list")
    )
}
