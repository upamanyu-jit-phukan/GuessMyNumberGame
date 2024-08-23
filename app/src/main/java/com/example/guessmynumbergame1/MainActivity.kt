package com.example.guessmynumbergame1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.guessmynumbergame1.ui.theme.GuessMyNumberGame1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessMyNumberGame1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GuessMyNumber(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GuessMyNumber(modifier: Modifier = Modifier) {
//    var numberOfTrials : Int
    var inputValue by remember { mutableStateOf("") }
    var Expanded by remember { mutableStateOf(false) }
    var numTrials = remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        OutlinedTextField(value = inputValue.value, onValueChange = { it ->
            inputValue = it
        },
            label = {Text("Enter value")})
        Box {
            Button(onClick = { Expanded = true }) {
                Text("Select Difficulty")
                androidx.compose.material3.Icon( imageVector = Icons.Default.ArrowDropDown,
                    "Arrow Down")
            }
            DropdownMenu(expanded = Expanded, onDismissRequest = { Expanded = false }) {
                DropdownMenuItem(text = {Text("Level 1")},
                    onClick = {
                        Expanded = true
                        numTrials.value = "10"

                    })
                DropdownMenuItem(text = {Text("Level 2")},
                    onClick = {
                        Expanded = true
                        numTrials.value = "10"

                    })
                DropdownMenuItem(text = {Text("Level 3")},
                    onClick = {
                        Expanded = true
                        numTrials.value = "10"

                    })
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GuessMyNumberPreview() {
    GuessMyNumberGame1Theme {
        GuessMyNumber()
    }
}