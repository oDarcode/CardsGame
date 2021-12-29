package ru.dariamikhailukova.cardsgame.repository

import retrofit2.Response
import retrofit2.http.Body
import ru.dariamikhailukova.cardsgame.api.RetrofitInstance
import ru.dariamikhailukova.cardsgame.model.*

class HeroesRepository {

    suspend fun getHero(): Response<LittleHero> =
        RetrofitInstance
            .api
            .getHero()

    suspend fun getHeroes(): Response<List<Hero>> =
        RetrofitInstance
            .api
            .getHeroes()

    suspend fun getCards(): Response<List<Card>> =
        RetrofitInstance
            .api
            .getCards()

    suspend fun getHeroes(battleTag: BattleTagInfo): Response<List<Hero>> =
        RetrofitInstance
            .api
            .getHeroes(battleTag)

    suspend fun getCards(battleTag: BattleTagInfo): Response<List<Card>> =
        RetrofitInstance
            .api
            .getCards(battleTag)

    suspend fun postUser(userInfo: UserInfo): Response<String> =
        RetrofitInstance
            .api
            .postUser(userInfo)
}