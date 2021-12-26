package ru.dariamikhailukova.cardsgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.dariamikhailukova.cardsgame.model.Hero
import ru.dariamikhailukova.cardsgame.model.ShortHero
import ru.dariamikhailukova.cardsgame.repository.HeroesRepository

class MainViewModel(private val repository: HeroesRepository): ViewModel() {

    val heroResponse: MutableLiveData<Response<Hero>> = MutableLiveData()
    val heroesResponse: MutableLiveData<Response<List<ShortHero>>> = MutableLiveData()

    fun getHero() {
        viewModelScope.launch {
            val response = repository.getHero()
            heroResponse.value = response
        }
    }

    fun getHeroes() {
        viewModelScope.launch {
            val response = repository.getHeroes()
            heroesResponse.value = response
        }
    }
}