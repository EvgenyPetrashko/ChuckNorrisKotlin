package com.template.chucknorris.data

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class JokeService {

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/jokes/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun getJokeApi(retrofit: Retrofit): JokeService = retrofit.create(JokeService::class.java)

}