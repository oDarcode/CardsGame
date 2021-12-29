package ru.dariamikhailukova.cardsgame.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LittleHero (
    val name: String,
    val health: Int,
    @SerializedName("hero_power_name")
    val powerName: String,
    @SerializedName("hero_power_text")
    val powerText: String,
    @SerializedName("hero_power_cost")
    val powerCost: Int,
    @SerializedName("hero_power_id")
    val powerId: String
): Serializable