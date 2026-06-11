package com.example.myapplication6

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication6.ui.theme.MyApplication6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication6Theme {
                WebsitesListScreen()
            }
        }
    }
}

val websites = listOf(
    "https://www.google.com",
    "https://developer.android.com",
    "https://kotlinlang.org"
)

@Composable
fun WebsitesListScreen() {
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(websites) { url ->
            Text(
                text = url,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        //intent wskazuje przekierowanie do url po otrzymaniu patha
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                    .padding(16.dp)
            )
        }
    }
}