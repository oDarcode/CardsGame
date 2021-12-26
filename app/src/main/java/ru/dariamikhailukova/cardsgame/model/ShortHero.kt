package ru.dariamikhailukova.cardsgame.model

import com.google.gson.annotations.SerializedName

data class ShortHero (
    @SerializedName("card_id")
    val cardId: String,
    @SerializedName("count_choosen")
    val countChosen: Int,
    @SerializedName("selection_frequency")
    val selectionFrequency: Double,
    @SerializedName("win_rate")
    val winRate: Double
)