package ru.dariamikhailukova.cardsgame.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.dariamikhailukova.cardsgame.model.Card
import ru.dariamikhailukova.cardsgame.model.LittleHero
import ru.dariamikhailukova.cardsgame.model.Hero
import ru.dariamikhailukova.cardsgame.model.UserInfo

interface HeroesApi {

    @GET("heroes/5")
    suspend fun getHero(): Response<LittleHero>

    @GET("heroes")
    suspend fun getHeroes(): Response<List<Hero>>

    @GET("cards")
    suspend fun getCards(): Response<List<Card>>

    @GET("heroes")
    suspend fun getHeroes(battleTag: String): Response<List<Hero>>

    @GET("cards")
    suspend fun getCards(battleTag: String): Response<List<Card>>

    @POST("users")
    suspend fun postUser(
        @Body userInfo: UserInfo
    ): Response<String>
}