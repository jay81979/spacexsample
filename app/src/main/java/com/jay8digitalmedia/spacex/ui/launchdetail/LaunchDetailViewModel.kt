package com.jay8digitalmedia.spacex.ui.launchdetail

import androidx.lifecycle.*
import com.jay8digitalmedia.spacex.repository.SpaceXRepository
import com.jay8digitalmedia.spacex.util.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class LaunchDetailViewModel @Inject constructor(
    private val spaceXRepository: SpaceXRepository
): ViewModel() {

    fun getLaunch(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = spaceXRepository.getLaunch(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getRocket(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = spaceXRepository.getRocket(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}