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
import androidx.compose.runtime.saveable.rememberSaveable
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

    if (complete.value && operation.value.isNotEmpty()) {
        // Create a mutable variable named answer and assign a value of 0
        var answer = 0
        // When statement using the operation variable
        when (operation.value) {
            "+" -> answer = leftNumber.value + rightNumber.value
            "-" -> answer = leftNumber.value - rightNumber.value
            "*" -> answer = leftNumber.value * rightNumber.value
            "/" -> if (rightNumber.value != 0) answer = leftNumber.value / rightNumber.value
        }
        displayText.value = answer.toString()
    }

    else if (operation.value.isNotEmpty() && !complete.value) {
        leftNumber.value = displayText.value.toInt()
        displayText.value = "0"
        complete.value = true
        displayText.value = rightNumber.value.toString()
    }

    else {
        displayText.value = leftNumber.value.toString()
    }

    fun numberPress(buttonNo: Int) {
        if (complete.value) {
            leftNumber.value = 0
            rightNumber.value = 0
            operation.value = ""
            complete.value = false
        }
        if (operation.value.isNotBlank() && !complete.value) {
            rightNumber.value = rightNumber.value * 10 + buttonNo
        } else if (operation.value.isBlank() && !complete.value) {
            leftNumber.value = leftNumber.value * 10 + buttonNo
        }
    }

    fun operationPress(oper: String) {
        if (!complete.value) {
            operation.value = oper
        }
    }

    fun equalsPress(){
        complete.value = true
    }

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
                CalcRow(onPress = { number -> numberPress(number) }, startNum = 7, numButtons = 3)
                CalcRow(onPress = { number -> numberPress(number) }, startNum = 4, numButtons = 3)
                CalcRow(onPress = { number -> numberPress(number) }, startNum = 1, numButtons = 3)
                Row {
                    CalcNumericButton(number = 0, onPress = { number -> numberPress(number) })
                    CalcEqualsButton(onpress = { equalsPress() })
                }
            }
            Column {
                CalcOperationButton(operationText = "+", onPress = { op -> operationPress(op) })
                CalcOperationButton(operationText = "-", onPress = { op -> operationPress(op) })
                CalcOperationButton(operationText = "*", onPress = { op -> operationPress(op) })
                CalcOperationButton(operationText = "/", onPress = { op -> operationPress(op) })
            }
        }
//        Row {
//            CalcNumericButton(number = 0, display = displayText)
//            CalcEqualsButton(display = displayText)
//        }
    }
}

@Composable
fun CalcRow(onPress: (number: Int) -> Unit, startNum: Int, numButtons: Int){
    val endNum = startNum + numButtons
    Row (modifier = Modifier.padding(0.dp)){
        for (num in startNum until endNum) {
            CalcNumericButton(onPress = { number -> onPress(number) }, number = num)
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
fun CalcNumericButton(onPress: (number: Int) -> Unit, number: Int){
    Button(
        onClick = { onPress(number) },
        modifier = Modifier.padding(4.dp)
    ){
        Text(number.toString())
    }
}

@Composable
fun CalcOperationButton(operationText: String, onPress: (operation: String) -> Unit) {
    Button(
        onClick = { onPress(operationText) },
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(operationText)
    }
}

@Composable
fun CalcEqualsButton(onpress: () -> Unit) {
    Button(
        onClick = { onpress() },
        modifier = Modifier.padding(4.dp)
    ) {
        Text("=")
    }
}

