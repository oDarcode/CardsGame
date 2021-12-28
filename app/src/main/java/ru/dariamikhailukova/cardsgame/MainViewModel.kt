package ru.dariamikhailukova.cardsgame

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.dariamikhailukova.cardsgame.model.Card
import ru.dariamikhailukova.cardsgame.model.LittleHero
import ru.dariamikhailukova.cardsgame.model.Hero
import ru.dariamikhailukova.cardsgame.repository.HeroesRepository

class MainViewModel(private val repository: HeroesRepository): ViewModel() {

    val heroResponse: MutableLiveData<Response<LittleHero>> = MutableLiveData()
    val heroesResponse: MutableLiveData<Response<List<Hero>>> = MutableLiveData()
    val cardsResponse: MutableLiveData<Response<List<Card>>> = MutableLiveData()

    fun getHero() {
        viewModelScope.launch {
            val response = repository.getHero()
            heroResponse.value = response
        }
    }

    fun getHeroes() {
        viewModelScope.launch {
            with(repository) {
                runCatching {
                    val response = repository.getHeroes()
                    heroesResponse.value = response
                }.onFailure {
                    Log.d("ERROR", it.message.toString())
                }
            }
        }
    }

    fun getCards() {
        viewModelScope.launch {
            with(repository) {
                runCatching {
                    val response = repository.getCards()
                    cardsResponse.value = response
                }.onFailure {
                    Log.d("ERROR", it.message.toString())
                }
            }
        }
    }

    fun getHeroes(battleTag: String) {
        viewModelScope.launch {
            with(repository) {
                runCatching {
                    val response = getHeroes(battleTag)
                    heroesResponse.value = response
                }.onFailure {
                    Log.d("ERROR", it.message.toString())
                }
            }
        }
    }

    fun getCards(battleTag: String) {
        viewModelScope.launch {
            with(repository) {
                runCatching {
                    val response = repository.getCards(battleTag)
                    cardsResponse.value = response
                }.onFailure {
                    Log.d("ERROR", it.message.toString())
                }
            }
        }
    }
}