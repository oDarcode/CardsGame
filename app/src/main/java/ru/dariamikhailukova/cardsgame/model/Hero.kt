package ru.dariamikhailukova.cardsgame.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Hero (
    @SerializedName("heroID")
    val heroId: String,
    @SerializedName("heroName")
    val heroName: String,
    @SerializedName("heroPowerName")
    val heroPowerName: String,
    @SerializedName("heroPowerText")
    val heroPowerText: String,
    @SerializedName("health")
    val health: Int,
    @SerializedName("heroPowerCost")
    val heroPowerCost: Int,
    @SerializedName("winRate")
    val winRate: Double,
    @SerializedName("averagePosition")
    val averagePosition: Double?,
    @SerializedName("selectionFrequency")
    val selectionFrequency: Double?,
    @SerializedName("heroPowerID")
    val heroPowerID: String,
    @SerializedName("ratingChange")
    val ratingChange: Int?
): Serializable