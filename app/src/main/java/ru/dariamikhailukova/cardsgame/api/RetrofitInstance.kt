package ru.dariamikhailukova.cardsgame.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dariamikhailukova.cardsgame.util.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder


object RetrofitInstance {

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api: HeroesApi by lazy {
        retrofit.create(HeroesApi::class.java)
    }
}