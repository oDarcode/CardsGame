package ru.dariamikhailukova.cardsgame.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Card(
    @SerializedName("cardID")
    val cardId: String,
    @SerializedName("cardName")
    val cardName: String,
    @SerializedName("heroID")
    val heroID: String?,
    @SerializedName("heroName")
    val heroName: String?,
    @SerializedName("cardRace")
    val cardRace: String,
    @SerializedName("cardText")
    val cardText: String,
    @SerializedName("health")
    val health: Int,
    @SerializedName("attack")
    val attack: Int,
    @SerializedName("techLevel")
    val techLevel: Int,
    @SerializedName("winRate")
    val winRate: Double,
    @SerializedName("averagePosition")
    val averagePosition: Double?,
    @SerializedName("flavorText")
    val flavorText: String?
): Serializable
