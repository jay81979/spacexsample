package com.jay8digitalmedia.spacex.di

import com.jay8digitalmedia.spacex.ui.launchdetail.LaunchDetailFragment
import com.jay8digitalmedia.spacex.ui.launchlist.LaunchListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeLaunchListFragment(): LaunchListFragment

    @ContributesAndroidInjector
    abstract fun contributeLaunchDetailFragment(): LaunchDetailFragment
}