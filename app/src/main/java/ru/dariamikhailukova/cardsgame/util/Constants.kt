package ru.dariamikhailukova.cardsgame.util

class Constants {

    companion object {
        const val BASE_URL = "http://192.168.192.133:5000/"

        const val ZERO = "0.0"
        const val MINUS = "-"
    }
}

fun changeEmptyToString(str: String?): String {
    return if (str.isNullOrEmpty()) {
        Constants.MINUS
    } else {
        str
    }
}