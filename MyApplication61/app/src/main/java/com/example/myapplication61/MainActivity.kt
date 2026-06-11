package com.example.myapplication61

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.myapplication61.ui.theme.MyApplication61Theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication61Theme {
                NavigationApp()
            }
        }
    }
}

// Nawigacja
@Composable
fun NavigationApp() {
    val navController = rememberNavController()

    // Obserwuj bieżącą trasę
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                // Przycisk Listy zadań
                NavigationBarItem(
                    selected = currentRoute == "lists" || currentRoute?.startsWith("detail") == true,
                    onClick = {
                        navController.navigate("lists") {
                            popUpTo("lists") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Text(
                            text = "📋",
                            fontSize = androidx.compose.ui.unit.TextUnit.Unspecified
                        )
                    },
                    label = { Text("Listy zadań") }
                )

                // Przycisk Oceny
                NavigationBarItem(
                    selected = currentRoute == "grades",
                    onClick = {
                        navController.navigate("grades") {
                            popUpTo("grades") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Text(
                            text = "⭐",
                            fontSize = androidx.compose.ui.unit.TextUnit.Unspecified
                        )
                    },
                    label = { Text("Oceny") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "lists",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("lists") {
                AssignmentListsScreen(
                    onListClick = { listId ->
                        navController.navigate("detail/$listId")
                    }
                )
            }
            composable("grades") {
                GradesSummaryScreen()
            }
            composable(
                "detail/{listId}",
                arguments = listOf(navArgument("listId") { type = NavType.StringType })
            ) { backStackEntry ->
                val listId = backStackEntry.arguments?.getString("listId") ?: return@composable
                ListDetailScreen(
                    listId = listId,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

// Ekran E1 - Lista zadań
@Composable
fun AssignmentListsScreen(onListClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(sampleAssignmentLists) { assignmentList ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onListClick(assignmentList.id) },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${assignmentList.subject} - Lista ${assignmentList.listNumber}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Ocena: ${assignmentList.grade} | Liczba zadań: ${assignmentList.tasks.size}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

// Ekran E2 - Podsumowanie ocen
@Composable
fun GradesSummaryScreen() {
    val gradesBySubject = sampleAssignmentLists
        .groupBy { it.subject }
        .mapValues { (_, lists) ->
            lists.map { it.grade }.average()
        }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(gradesBySubject.toList()) { (subject, averageGrade) ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = subject,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Średnia: %.2f".format(averageGrade),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

// Ekran E3 - Szczegóły listy
@Composable
fun ListDetailScreen(listId: String, onBack: () -> Unit) {
    val assignmentList = sampleAssignmentLists.find { it.id == listId }

    if (assignmentList == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Nie znaleziono listy")
        }
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            // Przycisk powrotu
            Button(
                onClick = onBack,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("← Powrót do listy zadań")
            }

            Text(
                text = "${assignmentList.subject} - Lista ${assignmentList.listNumber}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Ocena: ${assignmentList.grade}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Zadania:",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(assignmentList.tasks) { task ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Zadanie ${task.id}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Maksymalna liczba punktów: ${task.maxPoints}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

// Dane
data class Task(
    val id: Int,
    val description: String,
    val maxPoints: Int
)

data class AssignmentList(
    val id: String,
    val subject: String,
    val listNumber: Int,
    val grade: Double,
    val tasks: List<Task>
)

val sampleAssignmentLists = listOf(
    AssignmentList("PUM1_L1", "Programowanie Urządzeń Mobilnych 1", 1, 4.5, listOf(
        Task(1, "Implementacja FizzBuzz", 3),
        Task(2, "Sprawdzenie palindromu", 3),
        Task(3, "Trójkąt Pascala", 4)
    )),
    AssignmentList("PUM1_L2", "Programowanie Urządzeń Mobilnych 1", 2, 5.0, listOf(
        Task(1, "Funkcje rozszerzające", 4),
        Task(2, "Funkcje wyższego rzędu", 6)
    )),
    AssignmentList("SO_L1", "Systemy Operacyjne", 1, 3.5, listOf(
        Task(1, "Implementacja semafora", 5),
        Task(2, "Problem producenta-konsumenta", 5)
    )),
    AssignmentList("SO_L2", "Systemy Operacyjne", 2, 4.0, listOf(
        Task(1, "Algorytmy szeregowania CPU", 6),
        Task(2, "Zarządzanie pamięcią", 4)
    ))
)