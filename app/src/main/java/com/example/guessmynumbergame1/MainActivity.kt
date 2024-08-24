package com.example.guessmynumbergame1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.setValue
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
    var inputValue = remember { mutableStateOf("") }
    var Expanded = remember { mutableStateOf(false) }
    var Expanded2 = remember { mutableStateOf(false) }
    var numTrials = remember { mutableStateOf(5) }
    var numDivider = remember { mutableStateOf(3) }
    var output = remember { mutableStateOf("") }
    var count = remember { mutableStateOf(1) }
    var n = remember{ mutableStateOf(0) }

    fun generate(): Int {
        output.value = ""
        count.value = 1
        val x = 100 / numDivider.value
        var randomNumber = Random.nextInt(1, x + 1)
        randomNumber *= numDivider.value
        return randomNumber
    }

    fun test(n: Int) {
        val playerGuess = inputValue.value.toIntOrNull()
        if (playerGuess == null) {
            output.value = "Please enter valid number"
        } else {
            if (count.value - 1 == numTrials.value) {
                output.value = "Better luck next time. You have used all your chances for this game"
//                count.value = 1
            } else if (playerGuess == n) {
                output.value = "Congratulations!! Your were able to guess the " +
                        "number correctly in ${count.value} attempts"
            } else {
                count.value++
                val attemptsLeft = numTrials.value - count.value + 1
                output.value = "Try again. You have $attemptsLeft chances left"
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Text(
            "Number of trials left: ${numTrials.value}",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(value = inputValue.value, onValueChange = { it ->
            inputValue.value = it
            val p = n.value
            test(p)
        },
            label = { Text("Guess the number?") })
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Box {
                Button(onClick = { Expanded.value = true }) {
                    Text("Select Difficulty")
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        "Arrow Down"
                    )
                }
                DropdownMenu(
                    expanded = Expanded.value,
                    onDismissRequest = { Expanded.value = false }) {
                    DropdownMenuItem(text = { Text("Level 1: N%10 == 0 && 1<=N<=100") },
                        onClick = {
                            Expanded.value = false
                            numDivider.value = 10

                        })
                    DropdownMenuItem(text = { Text("Level 2: N%5 == 0 && 1<=N<=100") },
                        onClick = {
                            Expanded.value = false
                            numDivider.value = 5

                        })
                    DropdownMenuItem(text = { Text("Level 3: N%3 == 0 && 1<=N<=100") },
                        onClick = {
                            Expanded.value = false
                            numDivider.value = 3

                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { Expanded2.value = true }) {
                    Text("Select Number of Trials")
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        "Arrow Down"
                    )
                }
                DropdownMenu(
                    expanded = Expanded2.value,
                    onDismissRequest = { Expanded2.value = false }) {
                    DropdownMenuItem(text = { Text("Max. 5 trials") },
                        onClick = {
                            Expanded2.value = false
                            numTrials.value = 5
//                        test()
                            n.value = generate()

                        })
                    DropdownMenuItem(text = { Text("Max. 7 trials") },
                        onClick = {
                            Expanded2.value = false
                            numTrials.value = 7
//                        test()
                            n.value = generate()

                        })
                    DropdownMenuItem(text = { Text("Max. 10 trials") },
                        onClick = {
                            Expanded2.value = false
                            numTrials.value = 10
                            n.value = generate()

                        })
                }
            }


        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = output.value,
            style = MaterialTheme.typography.headlineMedium
        )

    }
}


@Preview(showBackground = true)
@Composable
fun GuessMyNumberPreview() {
    GuessMyNumberGame1Theme {
        GuessMyNumber()
    }
}
