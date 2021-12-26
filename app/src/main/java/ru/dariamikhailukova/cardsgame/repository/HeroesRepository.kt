package ru.dariamikhailukova.cardsgame.repository

import retrofit2.Response
import ru.dariamikhailukova.cardsgame.api.RetrofitInstance
import ru.dariamikhailukova.cardsgame.model.Hero
import ru.dariamikhailukova.cardsgame.model.ShortHero

class HeroesRepository {

    suspend fun getHero(): Response<Hero> =
        RetrofitInstance
            .api
            .getHero()

    suspend fun getHeroes(): Response<List<ShortHero>> =
        RetrofitInstance
            .api
            .getHeroes()
}