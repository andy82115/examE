package com.andyyeh.examE.launchActivity

import com.andyyeh.examE.databinding.ActivityLaunchBinding
import com.andyyeh.examE.di.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class LaunchActivityModule {

    @ActivityScoped
    @Provides fun ViewModel() : LaunchActivityViewModel{
        return LaunchActivityViewModel()
    }
}