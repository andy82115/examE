package com.andyyeh.examE.mainActivity

import com.andyyeh.examE.mainActivity.model.UserBean
import com.andyyeh.examE.mainActivity.model.UserModel
import com.andyyeh.examE.mvvmBase.BaseViewModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class MainActivityViewModel(repository: MainContract.Repository, userModel: UserModel) : BaseViewModel() {

    private val mRepository = repository
    private val mUserModel = userModel

    /**
     * @param consumer IntArray[0] present position start, InArray[1] present the size
     * @param limitAction do the action if the user size reach the limit
     * **/
    fun requestUserData(consumer: Consumer<MutableList<UserBean>>, limitAction: Action){
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
            val fData = mutableListOf<UserBean>()
            fData.addAll(mUserModel.datas)
            fData.addAll(it)
            Single.just(fData).subscribe(consumer)
        })
    }
}