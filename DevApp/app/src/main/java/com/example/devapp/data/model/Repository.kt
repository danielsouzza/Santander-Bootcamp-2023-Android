package com.example.devapp.data.model

import com.squareup.moshi.Json

data class Repository(
    val name: String,
    @field:Json(name="html_url")
    val htmlUrl:String,
    val description:String
)
