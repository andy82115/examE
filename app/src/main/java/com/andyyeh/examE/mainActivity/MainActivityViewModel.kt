package com.andyyeh.examE.mainActivity

import com.andyyeh.examE.Configuration
import com.andyyeh.examE.mainActivity.model.UserModel
import com.andyyeh.examE.mvvmBase.BaseViewModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class MainActivityViewModel(repository: MainActivityRepository, userModel: UserModel) : BaseViewModel() {

    private val TAG = MainActivityViewModel::class.java.simpleName
    private val DEBUG = Configuration.DEBUG

    private val mRepository = repository
    private val mUserModel = userModel

    /**
     * @param consumer IntArray[0] present position start, InArray[1] present the size
     * **/
    fun requestUserData(consumer: Consumer<Array<Int>>, limitAction: Action){
        var since = 0
        val size = mUserModel.datas.size
        if (size != 0) {
            since = mUserModel.datas[size - 1].id
        }

        if (size >= 100) {
            Completable.fromAction(limitAction).subscribe()
            return
        }

        mRepository.getDataFromInternet(since, Consumer {
            mUserModel.addData(it)
            val notifyParameter = arrayOf(mUserModel.datas.size, it.size)
            Single.just(notifyParameter).subscribe(consumer)
        })
    }
}