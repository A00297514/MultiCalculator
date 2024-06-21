package org.example.project

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun App() {
    CalcView()
}

@Composable
fun CalcView(){
    val displayText = remember { mutableStateOf("0") }

    // State variables with default values using rememberSaveable
    val leftNumber = rememberSaveable { mutableStateOf(0) }
    val rightNumber = rememberSaveable { mutableStateOf(0) }
    val operation = rememberSaveable { mutableStateOf("") }
    val complete = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Row {
            CalcDisplay(display = displayText)
        }
        Row {
            Column {
                CalcRow(display = displayText, startNum = 7, numButtons = 3)
                CalcRow(display = displayText, startNum = 4, numButtons = 3)
                CalcRow(display = displayText, startNum = 1, numButtons = 3)
                Row {
                    CalcNumericButton(number = 0, display = displayText)
                    CalcEqualsButton(display = displayText)
                }
            }
            Column {
                CalcOperationButton(operation = "+", display = displayText)
                CalcOperationButton(operation = "-", display = displayText)
                CalcOperationButton(operation = "*", display = displayText)
                CalcOperationButton(operation = "/", display = displayText)
            }
        }
//        Row {
//            CalcNumericButton(number = 0, display = displayText)
//            CalcEqualsButton(display = displayText)
//        }
    }
}

@Composable
fun CalcRow(display: MutableState<String>, startNum: Int, numButtons: Int){
    val endNum = startNum + numButtons
    Row (modifier = Modifier.padding(0.dp)){
        for (num in startNum until endNum) {
            CalcNumericButton(number = num, display = display)
        }
    }
}

@Composable
fun CalcDisplay(display: MutableState<String>){
    Text(
        text = display.value,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth()
    )

}

@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>){
    Button(
        onClick = { display.value += number.toString() },
        modifier = Modifier.padding(4.dp)
    ){
        Text(number.toString())
    }
}

@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>){
    Button(
        onClick = {  },
        modifier = Modifier
            .padding(4.dp)
    ){
        Text(operation)
    }
}

@Composable
fun CalcEqualsButton(display: MutableState<String>) {
    Button(
        onClick = { display.value = "0" },
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text("=")
    }
}

