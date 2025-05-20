package com.example.consumo_api.modules

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // 10.0.2.2 es como el emulador de Android accede a tu localhost.
    private const val BASE_URL = "http://10.0.2.2:3000/" // localhost para emulador

    // Esto crea una instancia de Retrofit lista para usarse:
    val api: AnimalApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimalApiService::class.java)
    }
}