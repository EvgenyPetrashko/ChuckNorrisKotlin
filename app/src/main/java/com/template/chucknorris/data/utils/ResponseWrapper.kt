package com.template.chucknorris.data.utils

sealed class ResponseWrapper<T> {
    class Success<T>(val data: T): ResponseWrapper<T>()
    class Failure<T>(val errorCause: ErrorCause): ResponseWrapper<T>()
}

sealed class ErrorCause{
    object NoInternetConnection : ErrorCause()
    object ServiceUnavailable: ErrorCause()
    object Unknown: ErrorCause()
}