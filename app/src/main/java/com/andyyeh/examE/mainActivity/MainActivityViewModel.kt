package com.andyyeh.examE.mainActivity

import com.andyyeh.examE.Configuration
import com.andyyeh.examE.mvvmBase.BaseViewModel

class MainActivityViewModel(repository: MainActivityRepository) : BaseViewModel() {

    var isSearchBarExtend = false

    private val TAG = MainActivityViewModel::class.java.simpleName
    private val DEBUG = Configuration.DEBUG

    private val mRepository = repository
}