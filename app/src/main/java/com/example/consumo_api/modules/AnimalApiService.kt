package com.example.consumo_api.modules

// Retrofit usa interfaces para definir cómo debe hacer las peticiones HTTP.

import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AnimalApiService {
   // Llama a GET /animales y devuelve una lista de animales.
    @GET("animales")
    suspend fun getAnimales(): List<Animal>

    @GET("animales/{id}")
    suspend fun getAnimalById(@Path("id") id: Int): Animal


    // Envía un objeto JSON al backend y lo crea en la base de datos.
    @POST("animales")
    suspend fun crearAnimal(@Body animal: Animal): Animal

    // Actualiza todos los campos del objeto con PUT
    @PUT("animales/{id}")
    @JvmSuppressWildcards
    suspend fun updateAnimal(@Path("id") id: Int,@Body animal: Animal): Animal

    // Solo actualiza algunos campos del animal usando PATCH.
    @PATCH("animales/{id}")
    @JvmSuppressWildcards //resuelve conflictos en Retrofit y en Room
    suspend fun patchAnimal(@Path("id") id: Int, @Body animal: Map<String, Any>): Animal

    @DELETE("animales/{id}")
    suspend fun deleteAnimal(@Path("id") id: Int) //Response<Unit>
}