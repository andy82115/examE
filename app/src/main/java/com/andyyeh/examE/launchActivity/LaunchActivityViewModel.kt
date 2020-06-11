package com.andyyeh.examE.launchActivity

import android.view.View
import androidx.databinding.ObservableField
import com.andyyeh.examE.mvvmBase.BaseViewModel

class LaunchActivityViewModel() : BaseViewModel(){
    //config val
    val TWINKLE_TIME = 500L
    val TWINKLE_SHOW_ALPHA = 0.5f
    val TWINKLE_HIDE_ALPHA = 0.25f
    //binding data//
    var vInfoTextVisible = ObservableField<Int>(View.GONE)
    var vInfoTextAlpha = ObservableField<Float>(TWINKLE_SHOW_ALPHA)
    var isStopTwinkle = false
    //binding fun//

    fun twinkleEvent(){
        when(vInfoTextAlpha.get()){
            0.5f -> vInfoTextAlpha.set(TWINKLE_HIDE_ALPHA)
            else -> vInfoTextAlpha.set(TWINKLE_SHOW_ALPHA)
        }
    }
}