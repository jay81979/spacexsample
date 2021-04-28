package com.jay8digitalmedia.spacex.api

import com.jay8digitalmedia.spacex.vo.Launch
import com.jay8digitalmedia.spacex.vo.Rocket
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApiService {

    @GET("launches")
    suspend fun getLaunches(): List<Launch>

    @GET("launches/{id}")
    suspend fun getLaunch(@Path("id") id: String): Launch

    @GET("rockets/{id}")
    suspend fun getRocket(@Path("id") id: String): Rocket
}