package com.template.chucknorris.di

import com.template.chucknorris.data.repository.JokeRepositoryImpl
import com.template.chucknorris.data.utils.ErrorHandler
import com.template.chucknorris.data.utils.ErrorHandlerImpl
import com.template.chucknorris.domain.repository.JokeRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun getJokeRepositoryImpl(jokeRepositoryImpl: JokeRepositoryImpl): JokeRepository

    @Binds
    abstract fun getErrorHandlerImpl(errorHandlerImpl: ErrorHandlerImpl): ErrorHandler
}