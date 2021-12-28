package ru.dariamikhailukova.cardsgame.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserInfo(
    @SerializedName("BattleTag")
    val battleTag: String,
    val stringedAuthorizationID: String,
    val authorizationType: String
): Serializable
