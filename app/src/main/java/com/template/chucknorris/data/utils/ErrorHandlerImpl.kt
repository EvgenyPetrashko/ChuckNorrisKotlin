package com.template.chucknorris.data.utils

import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(): ErrorHandler {
    override fun getError(throwable: Throwable): ErrorCause {
        return when (throwable){
            is java.io.IOException -> ErrorCause.NoInternetConnection
            else -> {
                ErrorCause.Unknown
            }
        }
    }
}