package com.jay8digitalmedia.spacex.repository

import com.jay8digitalmedia.spacex.api.SpaceXApiService
import javax.inject.Inject

class SpaceXRepository @Inject constructor(
    private val spaceXApiService: SpaceXApiService
) {
    suspend fun getLaunches() = spaceXApiService.getLaunches()

    suspend fun getLaunch(id: String) = spaceXApiService.getLaunch(id)

    suspend fun getRocket(id: String) = spaceXApiService.getRocket(id)
}