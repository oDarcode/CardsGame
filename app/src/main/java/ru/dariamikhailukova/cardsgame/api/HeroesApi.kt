package ru.dariamikhailukova.cardsgame.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.dariamikhailukova.cardsgame.model.*

interface HeroesApi {

    @GET("heroes/5")
    suspend fun getHero(): Response<LittleHero>

    @GET("heroes")
    suspend fun getHeroes(): Response<List<Hero>>

    @GET("cards")
    suspend fun getCards(): Response<List<Card>>

    @GET("heroes/player")
    suspend fun getHeroes(
        @Body battleTag: BattleTagInfo
    ): Response<List<Hero>>

    @GET("cards/player")
    suspend fun getCards(
        @Body battleTag: BattleTagInfo
    ): Response<List<Card>>

    @POST("users")
    suspend fun postUser(
        @Body userInfo: UserInfo
    ): Response<String>
}