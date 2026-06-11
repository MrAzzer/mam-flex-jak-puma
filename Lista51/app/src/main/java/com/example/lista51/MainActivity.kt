package com.example.lista51

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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.lista51.ui.theme.Lista51Theme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lista51Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CounterExample(modifier: Modifier = Modifier) {
    var l1 by remember { mutableStateOf("") }
    var l2 by remember { mutableStateOf("") }
    var wynik by remember { mutableStateOf(0) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {

        Text(
            text = "Kalkulator",
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )

        TextField(
            value = l1,
            onValueChange = { l1 = it },
            label = { Text("Liczba 1") }
        )

        TextField(
            value = l2,
            onValueChange = { l2 = it },
            label = { Text("Liczba 2") }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            onClick = {
                val n1 = l1.toIntOrNull() ?: 0
                val n2 = l2.toIntOrNull() ?: 0
                wynik = n1 + n2
            }
        ) {
            Text(text = "+")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            onClick = {
                val n1 = l1.toIntOrNull() ?: 0
                val n2 = l2.toIntOrNull() ?: 0
                wynik = n1 - n2
            }
        ) {
            Text(text = "-")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            onClick = {
                val n1 = l1.toIntOrNull() ?: 0
                val n2 = l2.toIntOrNull() ?: 0
                wynik = n1 * n2
            }
        ) {
            Text(text = "*")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            onClick = {
                val n1 = l1.toIntOrNull() ?: 0
                val n2 = l2.toIntOrNull() ?: 0
                wynik = n1 / n2
            }
        ) {
            Text(text = "/")
        }

        Text(
            text = "Wynik: $wynik",
            fontSize = 30.sp
        )
    }
}