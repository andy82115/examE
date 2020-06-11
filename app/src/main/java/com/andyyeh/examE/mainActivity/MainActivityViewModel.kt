package com.andyyeh.examE.mainActivity

import com.andyyeh.examE.Configuration
import com.andyyeh.examE.mvvmBase.BaseViewModel
import io.reactivex.functions.Consumer

class MainActivityViewModel(repository: MainActivityRepository) : BaseViewModel() {

    private val TAG = MainActivityViewModel::class.java.simpleName
    private val DEBUG = Configuration.DEBUG

    private val mRepository = repository

    fun requestUserData(){
        mRepository.getDataFromInternet(0, 1, Consumer {

        })
    }
}