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
import kotlin.random.Random

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
    var Expanded2 by remember { mutableStateOf(false) }
    var numTrials by remember { mutableStateOf(0) }
    var numDivider by remember { mutableStateOf(3) }
    var output by remember { mutableStateOf("") }
    var count by remember { mutableStateOf(1) }

    fun generate() {
        output = ""
        count = 1
        val x = 100/numDivider
        var randomNumber = Random.nextInt(1, x+1)
        randomNumber *= numDivider
        return randomNumber
    }

    fun test(n: Int) {
        val playerGuess = inputValue.toIntOrNull()
        if(playerGuess == null) {
            output = "Please enter valid number"
        }
        if (count-1==numTrials) {
            output = "Better luck next time. You have used all your chances for this game"
            count = 1
        }
        else if(inputValue==n) {
            output = "Congratulations!! Your were able to guess the " +
                    "number correctly in $count attempts"
        }
        else {
            count++
            val attemptsLeft = numTrials - count + 1
            output = "Try again. You have $attemptsLeft chances left"
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Text("Number of trials left: $numTrials",
            style = MaterialTheme.typography.headlineLarge
        )
        OutlinedTextField(value = inputValue.value, onValueChange = { it ->
            var inputValueString = inputValue.toString
            inputValueString = it
        },
            label = {Text("Guess the number?")})
        Box {
            Button(onClick = { Expanded = true }) {
                Text("Select Difficulty")
                androidx.compose.material3.Icon( imageVector = Icons.Default.ArrowDropDown,
                    "Arrow Down")
            }
            DropdownMenu(expanded = Expanded, onDismissRequest = { Expanded = false }) {
                DropdownMenuItem(text = {Text("Level 1: N%10 == 0 && 1<=N<=100")},
                    onClick = {
                        Expanded = true
                        numDivider = 10

                    })
                DropdownMenuItem(text = {Text("Level 2: N%5 == 0 && 1<=N<=100")},
                    onClick = {
                        Expanded = true
                        numDivider = 5

                    })
                DropdownMenuItem(text = {Text("Level 3: N%3 == 0 && 1<=N<=100")},
                    onClick = {
                        Expanded = true
                        numDivider = 3

                    })
            }
            Button(onClick = { Expanded = true }) {
                Text("Select Number of Trials")
                androidx.compose.material3.Icon( imageVector = Icons.Default.ArrowDropDown,
                    "Arrow Down")
            }
            DropdownMenu(expanded = Expanded, onDismissRequest = { Expanded2 = false }) {
                DropdownMenuItem(text = { Text("Max. 5 trials") },
                    onClick = {
                        Expanded2 = false
                        numTrials = 5
//                        test()
                        val n = generate()
                        test(n)
                    })
                DropdownMenuItem(text = { Text("Max. 7 trials") },
                    onClick = {
                        Expanded2 = false
                        numTrials = 7
//                        test()
                        val n = generate()
                        test(n)
                    })
                DropdownMenuItem(text = { Text("Max. 10 trials") },
                    onClick = {
                        Expanded2 = false
                        numTrials = 10
                        val n = generate()
                        test(n)
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
