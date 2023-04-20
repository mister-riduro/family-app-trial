package com.example.familyapp.di

import com.example.familyapp.data.FamilyApi
import com.example.familyapp.repository.DefaultRepository
import com.example.familyapp.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Containers for dependencies that live specific amount of time.

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFamilyApi(): FamilyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FamilyApi::class.java)

//    @Singleton
//    @Provides
//    fun provideDefaultRepository(api: FamilyApi): Repository = DefaultRepository(api)
}