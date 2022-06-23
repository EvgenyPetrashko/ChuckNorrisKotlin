package com.template.chucknorris.data.repository

import com.template.chucknorris.data.JokeApi
import com.template.chucknorris.data.entity.JokeFromServer
import com.template.chucknorris.data.utils.ErrorHandler
import com.template.chucknorris.data.utils.ResponseWrapper
import com.template.chucknorris.domain.repository.JokeRepository

class JokeRepositoryImpl constructor(
    private val jokeApi: JokeApi,
    private val errorHandler: ErrorHandler
) : JokeRepository {
    override suspend fun getJoke(): ResponseWrapper<JokeFromServer> {
        return try {
            ResponseWrapper.Success(data = jokeApi.randomJoke())
        } catch (t: Throwable) {
            ResponseWrapper.Failure(errorHandler.getError(t))
        }
    }
}