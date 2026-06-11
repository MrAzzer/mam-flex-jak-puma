package com.example.lista5

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.lista5.ui.theme.Lista5Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lista5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CounterExample()
                }}}}
@Composable
fun CounterExample() {
    var counter by remember { mutableIntStateOf(0) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        Text(
            text = counter.toString(),
            fontSize = 250.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            onClick = { counter++ }
        ) {
            Text(text = "Count UP")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            onClick = { counter-- }
        ) {
            Text(text = "Count DOWN")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            onClick = { counter=0 }
        ) {
            Text(text = "Reset")
        }
    }
}}