package com.andyyeh.examE.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule{
    @Provides
    fun bindContext(application: Application) : Context{
        return application.applicationContext
    }

    @Provides
    fun basicInfo(application: Application) : BasicInfo{
        val appInfo = application.applicationInfo
        val labelRes = appInfo.labelRes
        val appName = if (labelRes == 0) appInfo.nonLocalizedLabel.toString() else application.getString(labelRes)
        return BasicInfo(appName, labelRes)
    }
}