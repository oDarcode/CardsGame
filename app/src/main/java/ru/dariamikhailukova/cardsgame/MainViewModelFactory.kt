package ru.dariamikhailukova.cardsgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.dariamikhailukova.cardsgame.repository.HeroesRepository

class MainViewModelFactory(
    private val repository: HeroesRepository
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}