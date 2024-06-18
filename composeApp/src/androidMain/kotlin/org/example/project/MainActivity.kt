package org.example.project

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
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
fun AppAndroidPreview() {
    App()
}

@Composable
fun CalcView(){

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

