package com.example.plantgard.response

import java.io.Serializable

data class LoginResponse(
    val message: String,
    val errors: Any?, // Gunakan Any jika nilai bisa berupa null atau tipe lain
    val data: TokenData? // Tambahkan data sebagai objek, bukan String
) : Serializable

data class TokenData(
    val token: String,
    val refresh_token: String
) : Serializable
