package ru.dariamikhailukova.cardsgame.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserInfo(
    @SerializedName("battleTag")
    val battleTag: String,
    val stringedAuthorizationID: String,
    val authorizationType: String
): Serializable
