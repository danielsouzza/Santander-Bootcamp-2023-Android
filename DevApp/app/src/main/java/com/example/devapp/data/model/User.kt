package com.example.devapp.data.model

import com.squareup.moshi.Json

data class User(
    val login:String= "",
    val name:String = "",
    val bio: String = "",
    @field:Json(name="avatar_url")
    val avatarUrl: String = "",
    @field:Json(name="html_url")
    val htmlUrl: String = ""
)
