package com.template.chucknorris.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeService {

    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/jokes/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getJokeApi(retrofit: Retrofit): JokeService = retrofit.create(JokeService::class.java)

}