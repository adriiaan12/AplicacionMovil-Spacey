package com.example.consumo_api.modules

// Este data class representa el objeto que usarás en la app y que corresponde a lo que la API retorna.

data class Animal(
    val id: Int,
    val nombre: String,
    val especie: String,
    val edad: Int,
    val habitat: String,
    val enPeligro: Boolean
)

// Equivale a cada entrada de la API animales que tenías en Node.js.