package com.template.chucknorris.data

import com.template.chucknorris.data.entities.JokeFromServer
import retrofit2.http.GET

interface JokeApi {
    @GET("random")
    suspend fun randomJoke(): JokeFromServer
}