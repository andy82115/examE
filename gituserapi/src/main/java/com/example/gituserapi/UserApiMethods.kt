package com.example.andynovelapi.internetApi

import com.example.andyutils.designPatternUtils.SingletonHolder
import com.example.gituserapi.beans.NetUserBean
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserApiMethods private constructor(appName: String){

    private val baseUrl = "https://api.github.com/"
    private val perPage = 20;
    private val mApiService = UserApiManager(baseUrl, appName).getUserApiService()

    companion object : SingletonHolder<UserApiMethods, String>(::UserApiMethods)

    private fun <T> apiSubscribe(observable: Observable<T>, observer: Observer<T>){
        observable.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    fun getUserData(observer: Observer<List<NetUserBean>>, since: Int){
        apiSubscribe(mApiService!!.getUserInfo(since, perPage), observer)
    }
}