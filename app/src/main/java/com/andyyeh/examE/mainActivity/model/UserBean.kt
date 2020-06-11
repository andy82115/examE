package com.andyyeh.examE.mainActivity.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import javax.inject.Inject


data class UserBean(var imgUrl: String,
                    var userName: String,
                    var isStuff: Boolean)