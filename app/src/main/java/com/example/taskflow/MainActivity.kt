package com.example.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskflow.ui.theme.TaskFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskFlowTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { Home(navController) }
                        composable("minhasTarefas") { MinhasTarefas() }
                        composable("criarTarefa") { CriarTarefa(navController) }
                    }
                }
            }
        }
    }
}


@Composable
fun Home(navController: NavHostController) {
    Scaffold { paddingValues ->
        Column(
            Modifier.fillMaxSize().padding(
                horizontal = paddingValues.calculateTopPadding(),
                vertical = 90.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Image(
                    painterResource(R.drawable.lucas),
                    contentDescription = "logo"
                )
                Spacer(Modifier.height(40.dp))
                Text("  Ordene e crie tarefas!!! ")
            }

            Column(
                Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {}

                Column {
                    CustomAssistChip("MINHAS TAREFAS", "poison") {
                        navController.navigate("minhasTarefas")
                    }
                    Spacer(modifier = Modifier.height(1.dp))
                    CustomAssistChip("  CRIAR TAREFAS  ", "poison") {
                        navController.navigate("criarTarefa")
                    }
                }
            }
        }
    }
}

@Composable
fun CustomAssistChip(text: String, type: String, onClick: () -> Unit = {}) {
    ElevatedAssistChip(
        onClick = onClick,
        label = {
            Text(text)
        },
        colors = AssistChipDefaults
            .elevatedAssistChipColors(
                containerColor = chooseColor(type),
                labelColor = Color.White
            )
    )
}

fun chooseColor(type: String): Color {
    return when (type) {
        "roxo" -> Color(0xFF662D91)
        else -> Color.Black
    }
}
