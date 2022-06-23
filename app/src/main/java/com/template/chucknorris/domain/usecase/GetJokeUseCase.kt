package com.template.chucknorris.domain.usecase

import com.template.chucknorris.data.utils.ErrorCause
import com.template.chucknorris.data.utils.ResponseWrapper
import com.template.chucknorris.domain.entity.JokeWrapper
import com.template.chucknorris.domain.repository.JokeRepository
import javax.inject.Inject

class GetJokeUseCase @Inject constructor(private val jokeRepository: JokeRepository) {
    suspend fun execute(): JokeWrapper {
        return when (val jokeFromServerWrapper = jokeRepository.getJoke()){
            is ResponseWrapper.Success -> JokeWrapper.Joke(value = jokeFromServerWrapper.data.value)
            is ResponseWrapper.Failure -> {
                val returnedError: String = when (jokeFromServerWrapper.errorCause){
                    is ErrorCause.NoInternetConnection -> "Please turn on your Internet"
                    is ErrorCause.ServiceUnavailable -> "Service is currently unavailable. Please try again later"
                    is ErrorCause.Unknown -> "Error occurred. Please try again later"
                }
                JokeWrapper.Error(error = returnedError)
            }
        }
    }
}