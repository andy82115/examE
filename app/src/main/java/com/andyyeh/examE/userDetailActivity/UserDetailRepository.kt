package com.andyyeh.examE.userDetailActivity

import android.annotation.SuppressLint
import com.andyyeh.examE.BeanTransformer
import com.andyyeh.examE.di.BasicInfo
import com.example.andynovelapi.internetApi.UserApiMethods
import com.example.andynovelapi.internetApi.UserObserver
import com.example.andynovelapi.internetApi.UserObserverListener
import com.example.gituserapi.beans.NetUserDetailBean
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class UserDetailRepository (basicInfo: BasicInfo) : UserDetailContract.Repository{

    private val mBasicInfo = basicInfo

    override fun getUserDetailFromInternet(login: String, consumer: Consumer<UserDetailBean>) {
        val onNextListener = object : UserObserverListener.ObserverOnNextListener<NetUserDetailBean>{
            @SuppressLint("CheckResult")
            override fun onNext(t: NetUserDetailBean) {
                Single.just(t)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(fun(bean: NetUserDetailBean) : UserDetailBean {
                        return BeanTransformer.instance.netUserDetailBean2UserDetailBean(bean)
                    })
                    .subscribe(consumer)
            }
        }

        val userObserver = UserObserver(onNextListener)
        UserApiMethods.getInstance(mBasicInfo.appName).getUserDataByLogin(userObserver, login)
    }
}