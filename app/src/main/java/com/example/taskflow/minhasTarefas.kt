package com.example.taskflow

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import com.example.taskflow.model.Tarefa
import com.example.taskflow.repository.TarefaRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinhasTarefas() {
    val context = LocalContext.current
    val repository = remember { TarefaRepository(context) }
    val tarefas = remember { mutableStateOf(listOf<Tarefa>()) }

    LaunchedEffect(Unit) {
        tarefas.value = repository.getAllTarefas()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "TaskFlow",
                        style = TextStyle(color = Color(0xFF4A148C))
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Minhas Tarefas:", style = TextStyle(color = Color.Black))
            Spacer(modifier = Modifier.height(16.dp))
            // lista de tarefas
            tarefas.value.forEach { tarefa ->
                Text(text = tarefa.titulo)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
