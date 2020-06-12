package com.andyyeh.examE.userDetailActivity

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.andyyeh.examE.mvvmBase.BaseViewModel
import com.bumptech.glide.Glide
import io.reactivex.functions.Consumer

class UserDetailViewModel(repository: UserDetailRepository) : BaseViewModel(){

    private val mRepository = repository

    var imgUrl = ObservableField<String>("")
    var name = ObservableField<String>("")
    var details = ObservableField<String>("")
    var login = ObservableField<String>("")
    var location = ObservableField<String>("")
    var blog = ObservableField<String>("")
    var siteAdmin = ObservableField<Boolean>(false)

    fun requestDetailData(loginId: String){
        mRepository.getUserDetailFromInternet(loginId, Consumer {
            imgUrl.set(it.imgUrl)
            name.set(it.name)
            if (it.bio != null) {details.set(it.bio)}
            login.set(it.login)
            location.set(it.location)
            siteAdmin.set(it.siteAdmin)
        })
    }
}

@BindingAdapter("imgUrl")
fun loadImg(view: ImageView, url: String){
    Glide.with(view).load(url).into(view)
}