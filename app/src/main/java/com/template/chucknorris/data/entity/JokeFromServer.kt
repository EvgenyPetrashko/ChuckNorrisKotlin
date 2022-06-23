package com.template.chucknorris.data.entity

data class JokeFromServer(
    var categories: List<String?>,
    var created_at: String,
    var icon_url: String,
    var id: String,
    var updated_at: String,
    var url: String,
    var value: String
    )
