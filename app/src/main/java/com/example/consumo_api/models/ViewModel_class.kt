package com.example.consumo_api.models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumo_api.modules.Animal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.consumo_api.modules.AnimalViewModel
import com.example.consumo_api.modules.RetrofitClient


//Esta clase extiende ViewModel, lo que permite que sobreviva a cambios de configuración
// como rotaciones de pantalla.

class ViewModel_class:ViewModel() {

    // _uiState es una fuente mutable del estado.

    //   uiState es la versión inmutable expuesta a la UI para observar cambios de estado.

    //  StateFlow es un flujo de datos observable que mantiene un estado actual.


    var message by mutableStateOf("")
    private val api = RetrofitClient.api

    private val _animales = MutableStateFlow<List<Animal>>(emptyList())
    var animalDetail by mutableStateOf<Animal?>(null)
        private set


    val animales: StateFlow<List<Animal>> = _animales

    private val _uiState = MutableStateFlow(ViewModel_form())
    val uiState: StateFlow<ViewModel_form> = _uiState

  //  Usa update para modificar el valor actual del estado.

   // it.copy(...) crea una copia del estado anterior, con el nuevo valor actualizado.
  //  Este patrón se repite para apellido1, apellido2, dni, y email.

    fun onNombreChange(nuevoNombre: String) {
        _uiState.update { it.copy(nombre = nuevoNombre) }
    }

    fun onApellido1Change(nuevoApellido1: String) {
        _uiState.update { it.copy(apellido1 = nuevoApellido1) }
    }

    fun onApellido2Change(nuevoApellido2: String) {
        _uiState.update { it.copy(apellido2 = nuevoApellido2) }
    }

    fun onDniChange(nuevotlf: String) {
        _uiState.update { it.copy(tlf = nuevotlf) }
    }

    fun onEmailChange(nuevoEmail: String) {
        _uiState.update { it.copy(email = nuevoEmail) }
    }
    fun validarYEnviar() {
        val state = _uiState.value
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

        when {
            state.nombre.isBlank() -> setError("El nombre es obligatorio")
            state.apellido1.isBlank() -> setError("El primer apellido es obligatorio")
            state.tlf.length < 9 -> setError("El tlf debe tener al menos 9 caracteres")
            !emailRegex.matches(state.email) -> setError("El email no tiene un formato válido")
            else -> {
                _uiState.update {
                    it.copy(errorMensaje = null, envioExitoso = true)
                }
            }
        }
    }

    fun obtenerAnimales() {
        viewModelScope.launch {
            try {
                _animales.value = api.getAnimales()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al OBTENER animales", e)
            }
        }
    }

    fun agregarAnimal(animal: Animal) {
        viewModelScope.launch {
            try {
                val created = api.crearAnimal(animal)
                message = "Creado: ${created.nombre}"
                obtenerAnimales()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al AGREGAR animal", e)
            }
        }
    }

    private fun setError(mensaje: String) {
        _uiState.update { it.copy(errorMensaje = mensaje, envioExitoso = false) }
    }

}

// Tenemos una UI reactiva: cuando el usuario escribe algo,
// la función correspondiente se llama y actualiza el estado.

// Gracias a StateFlow, la UI que observe uiState se volverá a componer
// automáticamente con los nuevos datos.