package com.example.consumo_api.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.consumo_api.R







@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp), // Mantén el padding general

            verticalArrangement = Arrangement.SpaceBetween // Coloca el primero y el último en los extremos
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logos),
                    contentDescription = "Logo de la aplicación",
                    modifier = Modifier
                        .size(160.dp)
                        .padding(bottom = 80.dp)
                )
            }

            // Este Spacer ocupará todo el espacio restante, empujando el texto hacia abajo
            Spacer(modifier = Modifier.weight(0.5f))
            // Texto en la parte inferior
            Text(
                text = "Adrián Sánchez Álvarez\n" +
                        "Manuel Saucedo Gonzalez\n" +
                        "Yeray Via Alba",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 16.sp, lineHeight = 16.sp),
                textAlign = TextAlign.Start
            )
        }
    }
}



