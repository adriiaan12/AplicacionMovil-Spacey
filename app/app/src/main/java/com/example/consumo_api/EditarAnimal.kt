package com.example.consumo_api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.consumo_api.modules.Animal
import com.example.consumo_api.modules.AnimalViewModel




@Composable
fun AnimalUpdateScreen(viewModel: AnimalViewModel) {
    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var habitat by remember { mutableStateOf("") }
    var enPeligro by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = id, onValueChange = { id = it }, label = { Text("ID a actualizar") })
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = especie, onValueChange = { especie = it }, label = { Text("Especie") })
        OutlinedTextField(value = edad, onValueChange = { edad = it }, label = { Text("Edad") })
        OutlinedTextField(value = habitat, onValueChange = { habitat = it }, label = { Text("Hábitat") })

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = enPeligro, onCheckedChange = { enPeligro = it })
            Text("En peligro de extinción")
        }

        Button(onClick = {
            val animal = Animal(id.toInt(), nombre, especie, edad.toInt(), habitat, enPeligro)
            viewModel.editarAnimal(id.toInt(), animal)
        }) {
            Text("Actualizar Animal")
        }
    }
}