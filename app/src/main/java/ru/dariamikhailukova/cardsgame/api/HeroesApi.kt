package ru.dariamikhailukova.cardsgame.api

import retrofit2.Response
import retrofit2.http.GET
import ru.dariamikhailukova.cardsgame.model.Card
import ru.dariamikhailukova.cardsgame.model.LittleHero
import ru.dariamikhailukova.cardsgame.model.Hero

interface HeroesApi {

    @GET("heroes/5")
    suspend fun getHero(): Response<LittleHero>

    @GET("heroes")
    suspend fun getHeroes(): Response<List<Hero>>

    @GET("cards")
    suspend fun getCards(): Response<List<Card>>
}