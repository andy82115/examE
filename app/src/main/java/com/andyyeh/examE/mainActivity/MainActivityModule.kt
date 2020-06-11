package com.andyyeh.examE.mainActivity

import com.andyyeh.examE.di.ActivityScoped
import com.andyyeh.examE.di.BasicInfo
import com.andyyeh.examE.mainActivity.adapter.UserAdapter
import com.andyyeh.examE.mainActivity.model.UserModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule{

    @ActivityScoped
    @Provides fun mainActivityRepository(basicInfo: BasicInfo) : MainActivityRepository{
        return MainActivityRepository(basicInfo)
    }

    @ActivityScoped
    @Provides fun mainActivityViewModel(mainActivityRepository: MainActivityRepository) : MainActivityViewModel{
        return MainActivityViewModel(mainActivityRepository)
    }

    @ActivityScoped
    @Provides fun userModel(): UserModel{
        return UserModel()
    }

    @ActivityScoped
    @Provides fun userAdapter(userModel: UserModel): UserAdapter {
        return UserAdapter(userModel.datas)
    }
}