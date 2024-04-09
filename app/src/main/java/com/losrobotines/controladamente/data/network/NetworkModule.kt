package com.losrobotines.controladamente.data.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)//alcance del modulo, en este caso que cualquiera pueda inyectarse este modulo
object NetworkModule {

    @Provides
    @Singleton //para no crear un auth por llamada, sino que se use el primero y unico que se creó
    fun provideFirebaseAuth(): FirebaseAuth{
        return Firebase.auth
    }
}