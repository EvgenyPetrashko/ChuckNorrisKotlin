package com.template.chucknorris.domain.entity

sealed class JokeWrapper {
    class Joke(val value: String): JokeWrapper()
    class Error(val error: String): JokeWrapper()
}
