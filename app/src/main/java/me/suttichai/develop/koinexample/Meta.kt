package me.suttichai.develop.koinexample

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
)