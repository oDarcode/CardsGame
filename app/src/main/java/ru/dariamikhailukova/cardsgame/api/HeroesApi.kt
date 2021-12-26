package ru.dariamikhailukova.cardsgame.api

import retrofit2.Response
import retrofit2.http.GET
import ru.dariamikhailukova.cardsgame.model.Hero
import ru.dariamikhailukova.cardsgame.model.ShortHero

interface HeroesApi {

    @GET("heroes/5")
    suspend fun getHero(): Response<Hero>

    @GET("heroes")
    suspend fun getHeroes(): Response<List<ShortHero>>
}