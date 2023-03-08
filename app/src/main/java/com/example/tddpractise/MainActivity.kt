package com.example.tddpractise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tddpractise.model.User
import com.example.tddpractise.ui.theme.TDDPractiseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDDPractiseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val showUserScreen by remember {
                        mutableStateOf(true)
                    }

                    ToggleAddUserAndDisplayUsersScreen(showUserScreen = showUserScreen)
                }
            }
        }
    }
}

@Composable
fun ToggleAddUserAndDisplayUsersScreen(showUserScreen: Boolean) {
    if (showUserScreen) {
        DisplayAddUserScreen()
    } else {
        val list = listOf(User(1, "Michael", "Munatsi"))
        DisplayUsersScreen(list)
    }
}

@Composable
fun DisplayAddUserScreen(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }

    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .width(260.dp)
                .height(200.dp),
            verticalArrangement = Arrangement.SpaceBetween,

            ) {
            OutlinedTextField(
                value = username,
                label = { Text(text = "Name") },
                onValueChange = { newText ->
                    username = newText
                })
            OutlinedTextField(
                value = lastname,
                label = { Text(text = "Surname") },
                onValueChange = { newText: String ->
                    lastname = newText
                })

            Button(onClick = { /*TODO*/ }, modifier = modifier.width(250.dp)) {
                Text(text = "SUBMIT")
            }
        }
    }
}

@Composable
fun DisplayUsersScreen(users: List<User>, modifier: Modifier = Modifier) {
    LazyColumn(content = {
        items(users){ user ->
            Row {
                Text(text = user.name)
                Spacer(modifier = modifier.width(2.dp))
                Text(text = user.surname)
            }
        }
    },
        modifier = modifier.testTag("users list")
    )
}


@Preview(
    showBackground = true,
    device = Devices.PIXEL,
    showSystemUi = true,
)
@Composable
fun DefaultPreview() {
    TDDPractiseTheme {
        DisplayAddUserScreen()
    }
}