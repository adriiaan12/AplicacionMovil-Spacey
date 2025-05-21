package com.example.consumo_api

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.consumo_api.modules.AnimalViewModel

@Composable
fun AnimalPatchScreen(viewModel: AnimalViewModel) {
    var id by remember { mutableStateOf("") }
    var campo by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = id, onValueChange = { id = it }, label = { Text("ID del animal") })
        OutlinedTextField(value = campo, onValueChange = { campo = it }, label = { Text("Campo a editar (nombre, edad, etc)") })
        OutlinedTextField(value = valor, onValueChange = { valor = it }, label = { Text("Nuevo valor") })

        Button(onClick = {
            val convertedValue: Any = when (campo) {
                "edad", "id" -> valor.toIntOrNull() ?: 0
                "enPeligro" -> valor.toBooleanStrictOrNull() ?: false
                else -> valor
            }

            viewModel.patchAnimal(id.toInt(), mapOf(campo to convertedValue))
        }) {
            Text("Editar Parcialmente")
        }
    }
}