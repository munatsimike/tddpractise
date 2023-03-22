package com.example.tddpractise.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tddpractise.model.User
import com.example.tddpractise.ui.viewModel.UserViewModel

@Composable
fun DisplayAddUserScreen(
    usersViewModel: UserViewModel,
    modifier: Modifier = Modifier,

    ) {
    var username by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }

    Box(
        modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .width(260.dp)
                .height(200.dp),
            verticalArrangement = Arrangement.SpaceBetween,

            ) {
            OutlinedTextField(value = username,
                label = { Text(text = "Name") },
                onValueChange = { newText ->
                    username = newText
                })
            OutlinedTextField(value = lastname,
                label = { Text(text = "Surname") },
                onValueChange = { newText: String ->
                    lastname = newText
                })

            Button(
                onClick = { usersViewModel.saveUser(User(name = username, surname = lastname)) },
                modifier = modifier.width(250.dp)
            ) {
                Text(text = "SUBMIT")
            }
        }
    }
}