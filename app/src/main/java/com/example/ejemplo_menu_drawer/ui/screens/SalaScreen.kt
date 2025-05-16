package com.example.ejemplo_menu_drawer.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier




import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme

import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import com.example.ejemplo_menu_drawer.R

data class Sala(
    val imagenRes: Int,
    val descripcion: String,
    val descripcion2: String,
    val tablaInfo: List<Pair<String, String>> // cada par es una fila de la tabla (columna1, columna2)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalaScreen() {
    val salas = listOf(
        Sala(
            R.drawable.sala1,
            "El Falcon 9 es un cohete reutilizable de dos etapas diseñado y fabricado por SpaceX.",
            "Transportó la cápsula Crew Dragon en misiones tripuladas de la NASA y ha desplegado miles de satélites Starlink en la órbita baja terrestre.",
            listOf(
                "Capacidad" to "22,800 kg",
                "Velocidad máxima" to "27,000 km/h"
            )
        ),
        Sala(
            R.drawable.sala2,
            "Cohete lanzado en España y desarrollado por PLD Space en 2024",
            "Primer y unico cohete lanzado por una empresa española. Este se realiza con completo exito",
            listOf(
                "Capacidad" to "140,000 kg",
                "Velocidad máxima" to "39,900 km/h"
            )
        ),
        Sala(
            R.drawable.sala3,
            "El Ariane 5 es un cohete de lanzamiento desarrollado por la Agencia Espacial Europea (ESA).",
            "Realizó el lanzamiento del telescopio espacial James Webb en 2021.",
            listOf(
                "Capacidad" to "10,865 kg",
                "Velocidad máxima" to "28,000 km/h"
            )
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {

        // Fondo que ocupa toda la pantalla, incluido el área de la TopAppBar
        Image(
            painter = painterResource(id = R.drawable.fotofondito),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Scaffold(
            //El codigo de aqui abajo sirve para que ponga salas y la barrita esta blurreada
            topBar = {
                TopAppBar(
                    title = { Text("Salas") },
                    modifier = Modifier.height(48.dp),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black.copy(alpha = 0.5f), // Transparente pero visible
                        titleContentColor = Color.White
                    )
                )
            },
            containerColor = Color.Transparent // Hace que el fondo del Scaffold sea transparente
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(salas) { sala ->
                    SalaCard(sala)
                }
            }
        }
    }
}

@Composable
fun SalaCard(sala: Sala) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = sala.imagenRes),
            contentDescription = "Imagen de la sala",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = sala.descripcion,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = sala.descripcion2,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Tabla dinámica
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Información", color = Color.White, style = MaterialTheme.typography.bodyMedium)
                Text("Datos", color = Color.White, style = MaterialTheme.typography.bodyMedium)
            }
            Divider(color = Color.White.copy(alpha = 0.3f), thickness = 1.dp)

            sala.tablaInfo.forEach { (zona, capacidad) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(zona, color = Color.White)
                    Text(capacidad, color = Color.White)
                }
            }
        }
    }
}
