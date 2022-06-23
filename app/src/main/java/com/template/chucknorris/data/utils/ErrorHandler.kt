package com.template.chucknorris.data.utils

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorCause
}