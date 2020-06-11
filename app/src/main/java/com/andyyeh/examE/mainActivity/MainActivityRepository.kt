package com.andyyeh.examE.mainActivity

import android.annotation.SuppressLint
import com.andyyeh.examE.BeanTransformer
import com.andyyeh.examE.di.BasicInfo
import com.andyyeh.examE.mainActivity.model.UserBean
import com.example.andynovelapi.internetApi.UserApiMethods
import com.example.andynovelapi.internetApi.UserObserver
import com.example.andynovelapi.internetApi.UserObserverListener
import com.example.gituserapi.NetUserBean
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**implement contract for the unit test**/
class MainActivityRepository(basicInfo: BasicInfo) : MainContract.Repository{

    private val TAG = MainActivityRepository::class.java.simpleName
    private val mBasicInfo = basicInfo

    /**
     * @param since the id for request
     * @param consumer the callback for ViewModel
     * @see BeanTransformer transform the Online data to the Local
     * **/
    override fun getDataFromInternet(since: Int, consumer: Consumer<ArrayList<UserBean>>) {
        val onNextListener = object : UserObserverListener.ObserverOnNextListener<List<NetUserBean>>{
            @SuppressLint("CheckResult")
            override fun onNext(t: List<NetUserBean>) {
                Single.just(t)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(fun(netUserBeans: List<NetUserBean>) : ArrayList<UserBean> {
                        return BeanTransformer.instance.netUserBean2UserBean(netUserBeans)
                    })
                    .subscribe(consumer)
            }
        }

        val userObserver = UserObserver(onNextListener)
        UserApiMethods.getInstance(mBasicInfo.appName).getUserData(userObserver, since)
    }
}