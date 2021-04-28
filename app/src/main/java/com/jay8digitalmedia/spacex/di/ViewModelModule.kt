package com.jay8digitalmedia.spacex.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jay8digitalmedia.spacex.ui.launchdetail.LaunchDetailViewModel
import com.jay8digitalmedia.spacex.ui.launchlist.LaunchListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LaunchListViewModel::class)
    abstract fun bindLaunchListViewModel(launchListViewModel: LaunchListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LaunchDetailViewModel::class)
    abstract fun bindLaunchDetailViewModel(launchDetailViewModel: LaunchDetailViewModel): ViewModel

    // Factory
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}