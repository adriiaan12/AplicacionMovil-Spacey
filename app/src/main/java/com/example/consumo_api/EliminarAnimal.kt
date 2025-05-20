package com.example.consumo_api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import com.example.consumo_api.modules.AnimalViewModel
import com.example.consumo_api.modules.RetrofitClient

@Composable
fun AnimalDeleteScreen(viewModel: AnimalViewModel) {
    var id by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = id, onValueChange = { id = it }, label = { Text("ID del animal a eliminar") })

        Button(onClick = {
            viewModel.eliminarAnimal(id.toInt())
        }) {
            Text("Eliminar Animal")
        }
    }
}