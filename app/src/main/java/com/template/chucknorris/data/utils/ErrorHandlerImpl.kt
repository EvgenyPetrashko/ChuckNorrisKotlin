package com.template.chucknorris.data.utils

class ErrorHandlerImpl: ErrorHandler {
    override fun getError(throwable: Throwable): ErrorCause {
        return when (throwable){
            is java.io.IOException -> ErrorCause.NoInternetConnection
            else -> {
                ErrorCause.Unknown
            }
        }
    }
}