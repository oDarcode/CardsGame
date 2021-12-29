package ru.dariamikhailukova.cardsgame


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.dariamikhailukova.cardsgame.api.RetrofitInstance
import ru.dariamikhailukova.cardsgame.model.*
import ru.dariamikhailukova.cardsgame.repository.HeroesRepository

class MainViewModel(private val repository: HeroesRepository): ViewModel() {

    val heroResponse: MutableLiveData<Response<LittleHero>> = MutableLiveData()
    val heroesResponse: MutableLiveData<Response<List<Hero>>> = MutableLiveData()
    val cardsResponse: MutableLiveData<Response<List<Card>>> = MutableLiveData()

    val userResponse: MutableLiveData<Response<String>> = MutableLiveData()

    var battleTag: String? = null

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
                    Log.d(TAG, it.message.toString())
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
                    Log.d(TAG, it.message.toString())
                }
            }
        }
    }

    fun getHeroes(battleTag: BattleTagInfo) {
        viewModelScope.launch {
            with(repository) {
                runCatching {
                    val response = getHeroes(battleTag)
                    heroesResponse.value = response
                }.onFailure {
                    Log.d(TAG, it.message.toString())
                }
            }
        }
    }

    fun getCards(battleTag: BattleTagInfo) {
        viewModelScope.launch {
            with(repository) {
                runCatching {
                    val response = repository.getCards(battleTag)
                    cardsResponse.value = response
                }.onFailure {
                    Log.d(TAG, it.message.toString())
                }
            }
        }
    }

    fun postUser(userInfo: UserInfo) {
        viewModelScope.launch {
            with(repository) {
                runCatching {
                    val response = repository.postUser(userInfo)
                    userResponse.value = response
                }.onFailure {
                    Log.d(TAG, it.message.toString())
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}