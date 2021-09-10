package com.hfad.aplicmaterdis.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequesrOnServerAPI {
    @GET("planetary/apod")
    fun getPictures(@Query("api_key") apiKey: String):
            Call<PODServerResponse>
}