package com.losrobotines.controladamente.data.firebaseAuth

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(
        email: String,
        password: String,
        name: String,
        sexo: String/*, birthday :Date*/
    ): Resource<FirebaseUser>

    fun logout()
}