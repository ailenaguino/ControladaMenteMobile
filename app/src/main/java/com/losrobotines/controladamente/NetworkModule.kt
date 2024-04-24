package com.losrobotines.controladamente

import com.google.firebase.auth.FirebaseAuth
import com.losrobotines.controladamente.data.firebaseAuth.AuthRepository
import com.losrobotines.controladamente.data.firebaseAuth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)//alcance del modulo, en este caso que cualquiera pueda inyectarse este modulo
object NetworkModule {

  /*  @Provides
    @Singleton //para no crear un auth por llamada, sino que se use el primero y unico que se creó
    fun provideFirebaseAuth(): FirebaseAuth{
        return Firebase.auth
    }

   */
    @Provides
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

}