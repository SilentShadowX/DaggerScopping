package com.kpiega.daggerscopping.model

data class User(
        val name: String = "",
        val lastname: String = "",
        val login: String,
        val password: String,
        val token: String
) {
    fun getFullName() = "$name $lastname"

    fun getCredentials() = "Login = $login Password = $password"
}