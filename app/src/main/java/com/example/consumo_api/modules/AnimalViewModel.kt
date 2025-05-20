package com.example.consumo_api.modules

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimalViewModel : ViewModel() {

    //Mantiene el estado de la lista de animales

    var message by mutableStateOf("")
    private val api = RetrofitClient.api

    private val _animales = MutableStateFlow<List<Animal>>(emptyList())
    var animalDetail by mutableStateOf<Animal?>(null)
        private set


    val animales: StateFlow<List<Animal>> = _animales


    fun obtenerAnimales() {
        viewModelScope.launch {
            try {
                _animales.value = api.getAnimales()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al OBTENER animales", e)
            }
        }
    }

    fun buscarAnimalPorId(id: Int) {
        viewModelScope.launch {
            try {
                animalDetail = api.getAnimalById(id)
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al OBTENER animal por ID", e)
                animalDetail = null
            }
        }
    }
//POST
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

// PUT
    fun editarAnimal(id: Int, animal: Animal) {
        viewModelScope.launch {
            try {
                val mod = api.updateAnimal(id, animal)
                message = "Editado completamente: ${mod.id}"
                obtenerAnimales()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al EDITAR (PUT) animal", e)
            }
        }
    }
    // PATCH
    fun patchAnimal(id: Int, fields: Map<String, Any>) {
        viewModelScope.launch {
            try {
                val patched = api.patchAnimal(id, fields)
                message = "Editado parcialmente: ${patched.id}"
                obtenerAnimales()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al EDITAR PARCIALMENTE (PATCH) animal", e)
            }

        }
    }
// DELETE
    fun eliminarAnimal(id: Int) {
        viewModelScope.launch {
            try {
                api.deleteAnimal(id)
                message = "Eliminado ID: $id"
                obtenerAnimales()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al ELIMINAR animal", e)
            }
        }
    }
}