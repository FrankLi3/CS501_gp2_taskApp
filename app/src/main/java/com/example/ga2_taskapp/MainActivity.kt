package com.example.ga2_taskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ga2_taskapp.ui.theme.Ga2_taskAppTheme
import kotlinx.coroutines.launch

data class Task(val description: String, val isChecked: Boolean = false)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ga2_taskAppTheme {
                TaskTrackerScreen()
            }
        }
    }
}

@Composable
fun TaskTrackerScreen() {
    var taskList by remember { mutableStateOf(listOf<Task>()) }
    var popUp by remember { mutableStateOf(false) }
    var newTask by remember { mutableStateOf("") }
    var showDeletedMessage by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // Top Banner
        Column(modifier = Modifier.padding(innerPadding)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Task Tracker",
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                if (taskList.isEmpty()) {
                    item {
                        Text(
                            text = "click '+' to add task",
                            color = Color.Gray,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                } else {
                    items(taskList.size) { index ->
                        val task = taskList[index]
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = task.isChecked,
                                onCheckedChange = { isChecked ->
                                    taskList = taskList.toMutableList().apply {
                                        this[index] = this[index].copy(isChecked = isChecked)
                                    }
                                }
                            )
                            Text(
                                text = task.description,
                                color = if (task.isChecked) Color.Gray else Color.Black,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Divider()
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    FloatingActionButton(
                        onClick = { popUp = true },
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add New Task")
                    }
                    FloatingActionButton(
                        onClick = {
                            taskList = taskList.filter { !it.isChecked }
                            showDeletedMessage = true
                            coroutineScope.launch {
                                kotlinx.coroutines.delay(2000)
                                showDeletedMessage = false
                            }
                        }
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Done Tasks")
                    }
                }
            }

            if (showDeletedMessage) {
                Text(
                    text = "deleted done task",
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }

            if (popUp) {
                AlertDialog(
                    onDismissRequest = { popUp = false },
                    title = { Text(text = "Add new task") },
                    text = {
                        BasicTextField(
                            value = newTask,
                            onValueChange = { newTask = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    },
                    confirmButton = {
                        Button(onClick = {
                            if (newTask.isNotBlank()) {
                                taskList = taskList + Task(newTask)
                                newTask = ""
                                popUp = false
                            }
                        }) {
                            Text("Add")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { popUp = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ga2_taskAppTheme {
        TaskTrackerScreen()
    }
}