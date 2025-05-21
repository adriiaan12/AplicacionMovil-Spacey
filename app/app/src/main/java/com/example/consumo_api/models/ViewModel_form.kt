package com.example.consumo_api.models

//Se importan las herramientas necesarias para usar ViewModel (de arquitectura de Jetpack) y
// StateFlow (de Kotlin Coroutines) para el manejo reactivo del estado, es decir, que la app reaccione automáticamente a los cambios que haya en la UI


data class ViewModel_form(
    val nombre: String = "",
    val apellido1: String = "",
    val apellido2: String = "",
    val tlf: String = "",
    val email: String = "",
    val fechaini: String = "",
    val rol: String = "",
    val errorMensaje: String? = null,
    val envioExitoso: Boolean = false
)

