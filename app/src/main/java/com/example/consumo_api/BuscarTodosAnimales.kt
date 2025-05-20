package com.example.consumo_api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.consumo_api.modules.AnimalViewModel

@Composable
fun ListaAnimales(viewModel: AnimalViewModel = AnimalViewModel()) {
    val animales by viewModel.animales.collectAsState()
    val contexto = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.obtenerAnimales()
    }
    LazyColumn {
        items(animales) { animal ->
            Card( colors = CardDefaults.cardColors(containerColor = Color.Red,
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
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        generarPdf(contexto, animales)
    }) {
        Text("Exportar a PDF")
    }
}


// La forma más simple de mostrar los elementos cargados de la API
@Composable
fun ListaDeAnimales(viewModel: AnimalViewModel = AnimalViewModel()) {
    val animales by viewModel.animales.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.obtenerAnimales()
    }

    LazyColumn {
        items(animales) { animal ->
            Text("${animal.id}: ${animal.nombre} (${animal.especie})")
        }
    }
}
