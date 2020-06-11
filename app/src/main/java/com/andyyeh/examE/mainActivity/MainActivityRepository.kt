package com.andyyeh.examE.mainActivity

import android.util.Log
import com.andyyeh.examE.di.BasicInfo
import com.andyyeh.examE.mainActivity.model.UserBean
import com.example.andynovelapi.internetApi.UserApiMethods
import com.example.andynovelapi.internetApi.UserObserver
import com.example.andynovelapi.internetApi.UserObserverListener
import com.example.climateapi.Configuration
import com.example.gituserapi.NetUserBean
import io.reactivex.functions.Consumer

class MainActivityRepository(basicInfo: BasicInfo) : MainContract.Repository{

    private val TAG = MainActivityRepository::class.java.simpleName
    private val mBasicInfo = basicInfo

    override fun getDataFromInternet(since: Int, page: Int, consumer: Consumer<ArrayList<UserBean>>) {
        val onNextListener = object : UserObserverListener.ObserverOnNextListener<List<NetUserBean>>{
            override fun onNext(t: List<NetUserBean>) {
                if (Configuration.DEBUG) Log.e(TAG, t.toString())
            }
        }

        val userObserver = UserObserver(onNextListener)
        UserApiMethods.getInstance(mBasicInfo.appName).getUserData(userObserver, since, page)
    }
}