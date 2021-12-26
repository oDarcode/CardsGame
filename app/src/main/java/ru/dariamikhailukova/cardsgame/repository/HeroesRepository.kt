package ru.dariamikhailukova.cardsgame.repository

import retrofit2.Response
import ru.dariamikhailukova.cardsgame.api.RetrofitInstance
import ru.dariamikhailukova.cardsgame.model.Card
import ru.dariamikhailukova.cardsgame.model.LittleHero
import ru.dariamikhailukova.cardsgame.model.Hero

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
}