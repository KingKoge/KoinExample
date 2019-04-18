package me.suttichai.develop.koinexample

import io.reactivex.Flowable
import retrofit2.http.GET

interface Service {

    @GET("/characters")
    fun getCharacters(): Flowable<ApiResult<MutableList<Character>>>
}