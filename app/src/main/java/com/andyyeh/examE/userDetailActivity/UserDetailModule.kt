package com.andyyeh.examE.userDetailActivity

import com.andyyeh.examE.di.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class UserDetailModule {
    @ActivityScoped
    @Provides fun userDetailRepository() : UserDetailRepository{
        return UserDetailRepository()
    }

    @ActivityScoped
    @Provides fun userDetailViewModel() : UserDetailViewModel{
        return UserDetailViewModel()
    }
}