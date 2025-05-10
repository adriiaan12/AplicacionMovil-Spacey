package com.example.ejemplo_menu_drawer.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)



@Composable
fun TasksScreen()  {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Tareas") }) }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            Text("Aquí se mostrarán tus tareas")
        }
    }
}

// Pensando la solución como aparece a continuación daría un error ocurre ya que
// modifier = it no es válido directamente para Text, por lo que it en ese contexto
// es de tipo PaddingValues, no un Modifier.


//@Composable
//fun TasksScreen() {
 //   Scaffold(
  //      topBar = { TopAppBar(title = { Text("Tareas") }) }
  //  ) {
  //      Text("Aquí se mostrarán tus tareas", modifier = it)
  //  }
//}

