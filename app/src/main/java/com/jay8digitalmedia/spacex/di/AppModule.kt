package com.jay8digitalmedia.spacex.di

import android.content.Context
import com.jay8digitalmedia.spacex.SpaceXApplication
import com.jay8digitalmedia.spacex.api.SpaceXApiService
import com.jay8digitalmedia.spacex.repository.SpaceXRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule(private val app: SpaceXApplication) {

    @Singleton
    @Provides
    fun providesContext(): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun providesApplication(): SpaceXApplication {
        return app
    }

    @Singleton
    @Provides
    fun providesSpaceXService(): SpaceXApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpaceXApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesSpaceXRepository(spaceXApiService: SpaceXApiService): SpaceXRepository {
        return SpaceXRepository(spaceXApiService)
    }
}