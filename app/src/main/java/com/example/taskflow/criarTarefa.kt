package com.example.taskflow

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskflow.model.Tarefa
import com.example.taskflow.repository.TarefaRepository
import kotlinx.coroutines.launch
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CriarTarefa(navController: NavHostController) {
    var titulo by remember { mutableStateOf(TextFieldValue()) }
    var descricao by remember { mutableStateOf(TextFieldValue()) }
    val localContext = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val successMessage = "Tarefa salva com sucesso!"

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "TaskFlow",
                        style = TextStyle(color = Color(0xFF4A148C))
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val tarefa = Tarefa(
                        id = null,
                        titulo = titulo.text,
                        descricao = descricao.text
                    )
                    val repository = TarefaRepository(localContext)
                    val id = repository.insert(tarefa)
                    if (id > 0L) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = successMessage,
                                withDismissAction = true,
                                duration = SnackbarDuration.Long
                            )
                        }
                    }
                }
            ) {
                Icon(Icons.Filled.Save, contentDescription = "Salvar nova tarefa")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Crie sua Tarefa:",
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = titulo,
                onValueChange = { titulo = it },
                placeholder = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = descricao,
                onValueChange = { descricao = it },
                placeholder = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
