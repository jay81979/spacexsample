package com.jay8digitalmedia.spacex.ui.launchlist

import androidx.lifecycle.*
import com.jay8digitalmedia.spacex.repository.SpaceXRepository
import com.jay8digitalmedia.spacex.util.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class LaunchListViewModel @Inject constructor(
    private val spaceXRepository: SpaceXRepository
): ViewModel() {

    fun getLaunches() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = spaceXRepository.getLaunches()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}