package me.suttichai.develop.koinexample

import com.google.gson.annotations.SerializedName

data class ApiResult<T> constructor(
    @SerializedName("meta")
    val meta:Meta,
    @SerializedName("data")
    val data: T
)