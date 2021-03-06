package com.andyyeh.examE.di

import com.andyyeh.examE.launchActivity.LaunchActivity
import com.andyyeh.examE.launchActivity.LaunchActivityModule
import com.andyyeh.examE.mainActivity.MainActivity
import com.andyyeh.examE.mainActivity.MainActivityModule
import com.andyyeh.examE.userDetailActivity.UserDetailActivity
import com.andyyeh.examE.userDetailActivity.UserDetailModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule constructor(){
    @ActivityScoped
    @ContributesAndroidInjector(modules = [(LaunchActivityModule::class)])
    abstract fun LaunchActivity() : LaunchActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun MainActivity() : MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [(UserDetailModule::class)])
    abstract fun UserDetailActivity() : UserDetailActivity
}