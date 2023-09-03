package com.andyyeh.examE.userDetailActivity

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.andyyeh.examE.di.ActivityScoped
import com.andyyeh.examE.di.BasicInfo
import dagger.Module
import dagger.Provides

@Module
class UserDetailModule {
    @ActivityScoped
    @Provides fun userDetailRepository(basicInfo: BasicInfo) : UserDetailRepository{
        return UserDetailRepository(basicInfo)
    }

//    @ActivityScoped
//    @Provides fun userDetailViewModel(repository: UserDetailRepository, context: Context) : UserDetailViewModel{
//        return UserDetailViewModel(repository)
//    }
}