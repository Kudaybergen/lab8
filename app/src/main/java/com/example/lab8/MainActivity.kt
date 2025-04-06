package com.example.lab8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab8.ui.theme.LAB8Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB8Theme {
                RandomNumberScreen()
            }
        }
    }
}

@Composable
fun RandomNumberScreen() {
    var randomNumber by remember { mutableStateOf("0") }
    var isRunning by remember { mutableStateOf(false) }

    // When isRunning is true, the LaunchedEffect starts a coroutine that updates the number
    LaunchedEffect(isRunning) {
        while (isRunning) {
            randomNumber = (0..100).random().toString()
            delay(500) // update every 100 milliseconds
        }
    }

    // UI layout with a centered column containing the number and two buttons
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = randomNumber, fontSize = 36.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { isRunning = true }) {
                Text("Start")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { isRunning = false }) {
                Text("Stop")
            }
        }
    }
}