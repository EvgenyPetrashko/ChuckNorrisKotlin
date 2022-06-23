package com.template.chucknorris.data

import com.template.chucknorris.data.entities.JokeFromServer
import io.reactivex.Single
import retrofit2.http.GET

interface JokeApi {
    @GET("random")
    fun randomJoke(): Single<JokeFromServer?>?
}