package com.template.chucknorris.domain.repository

import com.template.chucknorris.data.entity.JokeFromServer
import com.template.chucknorris.data.utils.ResponseWrapper

interface JokeRepository {
    suspend fun getJoke(): ResponseWrapper<JokeFromServer>
}