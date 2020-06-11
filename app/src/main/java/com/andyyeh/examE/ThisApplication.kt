package com.andyyeh.examE

import com.andyyeh.examE.di.AppComponent
import com.andyyeh.examE.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ThisApplication constructor() : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent: AppComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)

        return appComponent
    }
}