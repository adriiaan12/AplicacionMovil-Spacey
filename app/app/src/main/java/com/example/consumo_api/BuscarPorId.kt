package com.example.consumo_api

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.consumo_api.modules.AnimalViewModel

@Composable
fun AnimalDetailScreen(viewModel: AnimalViewModel) {
    var id by remember { mutableStateOf("") }
    //  val animal by viewModel.animalBuscado.collectAsState()

    LaunchedEffect(Unit) {
        id.toIntOrNull()?.let { viewModel.buscarAnimalPorId(it) }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID del animal") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            id.toIntOrNull()?.let { viewModel.buscarAnimalPorId(it) }
        }) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))


        viewModel.animalDetail?.let { animal ->

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Red,
                ),

                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Id: ${animal.id}")
                    Text("Nombre: ${animal.nombre}")
                    Text("Especie: ${animal.especie}")
                    Text("Edad: ${animal.edad}")
                    Text("Hábitat: ${animal.habitat}")
                    Text("En peligro: ${if (animal.enPeligro) "Sí" else "No"}")
                }
            }

        }
    }
}
