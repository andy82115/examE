package com.andyyeh.examE.userDetailActivity

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.andyyeh.examE.mvvmBase.BaseViewModel
import com.bumptech.glide.Glide

class UserDetailViewModel : BaseViewModel(){

    var imgUrl = ObservableField<String>("")
    var name = ObservableField<String>("")
    var details = ObservableField<String>("")
    var login = ObservableField<String>("")
    var location = ObservableField<String>("l")
    var blog = ObservableField<String>("")
}

@BindingAdapter("imgUrl")
fun loadImg(view: ImageView, url: String){
    Glide.with(view).load(url).into(view)
}