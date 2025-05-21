package com.example.consumo_api.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable



import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.example.consumo_api.models.ViewModel_class

import com.example.consumo_api.modules.Animal
import com.example.consumo_api.modules.AnimalViewModel

import java.util.Calendar
import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: ViewModel_class) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val opcionesRol = listOf("Estudiante", "Profesor", "Otro")
    var enPeligro by remember { mutableStateOf(false) }

    val datePickerDialog = remember {
        DatePickerDialog(context, { _, y, m, d ->
            viewModel.onFechainiChange("%02d/%02d/%04d".format(d, m + 1, y))
        }, year, month, day)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            value = uiState.nombre,
            onValueChange = viewModel::onNombreChange,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )



        TextField(
            value = uiState.apellido1,
            onValueChange = viewModel::onApellido1Change,
            label = { Text("Primer Apellido") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = uiState.apellido2,
            onValueChange = viewModel::onApellido2Change,
            label = { Text("Segundo Apellido") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = uiState.tlf,
            onValueChange = viewModel::ontlfChange,
            label = { Text("Telefono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )



        TextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = uiState.fechaini,
            onValueChange = {},
            enabled = false, // evita edición manual
            label = { Text("Fecha de Nacimiento") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    datePickerDialog.show()
                }
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                readOnly = true,
                value = uiState.rol,
                onValueChange = {},
                label = { Text("Rol") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                opcionesRol.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            viewModel.onRolChange(opcion)
                            expanded = false
                        }
                    )
                }
            }
        }



        uiState.errorMensaje?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = enPeligro, onCheckedChange = { enPeligro = it })
            Text("En peligro")
        }

        Button(
            onClick = {
                viewModel.agregarAnimal(Animal(0,uiState.nombre,uiState.apellido1,uiState.tlf.toInt(),uiState.fechaini,enPeligro))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar")
        }

        // Enviar email si validación fue exitosa
        if (uiState.envioExitoso) {
            LaunchedEffect(Unit) {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "message/rfc822"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("diazcanoignacio@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Formulario de datos")
                    putExtra(
                        Intent.EXTRA_TEXT,
                        """
                        Nombre: ${uiState.nombre}
                        Apellido 1: ${uiState.apellido1}
                        Apellido 2: ${uiState.apellido2}
                        DNI: ${uiState.tlf}
                        Email: ${uiState.email}
                        """.trimIndent()
                    )
                }
                context.startActivity(Intent.createChooser(intent, "Enviar email con..."))
                // Reseteamos estado para evitar múltiples lanzamientos
                viewModel.onEmailChange("") // ejemplo para reset
            }
        }
    }
}