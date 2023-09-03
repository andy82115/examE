package com.andyyeh.examE.userDetailActivity

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.andyyeh.examE.Configuration
import com.andyyeh.examE.R
import com.andyyeh.examE.mvvmBase.BaseViewModel
import com.bumptech.glide.Glide
import io.reactivex.functions.Consumer
import kotlinx.coroutines.*

class UserDetailViewModel(repository: UserDetailRepository) : BaseViewModel(){

    private val mRepository = repository
    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())

    var imgUrl = ObservableField<String>("")
    var name = ObservableField<String>("")
    var details = ObservableField<String>("")
    var login = ObservableField<String>("")
    var location = ObservableField<String>("")
    var blog = ObservableField<String>("")
    var siteAdmin = ObservableField<Boolean>(false)

    var testLiveData = TestLiveData("Hello")

    /**
     * put data on the DataBinding Parameter
     * **/
    fun requestDetailData(loginId: String){
        mRepository.getUserDetailFromInternet(loginId, Consumer {
            imgUrl.set(it.imgUrl)
            name.set(it.name)
            if (it.bio != null) {details.set(it.bio)}
            login.set(it.login)
            location.set(it.location)
            siteAdmin.set(it.siteAdmin)
        })

        if (Configuration.DEBUG) Log.e("coroutine thread1: ", Thread.currentThread().name)

        coroutineScope.launch {

            if (Configuration.DEBUG) Log.e("coroutine thread3: ", Thread.currentThread().name)
            workWithIO()
            if (Configuration.DEBUG) Log.e("coroutine thread5: ", Thread.currentThread().name)

        }
    }

    private suspend fun workWithIO() = withContext(Dispatchers.IO){
        if (Configuration.DEBUG) Log.e("coroutine thread4:", Thread.currentThread().name)
    }

    override fun onCleared() {
        super.onCleared()
        if (Configuration.DEBUG) Log.e("detail vm onClear: ", "clear something")
    }
}

/**
 * this func provide for
 * @see R.id.vDetailUserIV
 * **/
@BindingAdapter("imgUrl")
fun loadImg(view: ImageView, url: String){
    Glide.with(view).load(url).into(view)
}