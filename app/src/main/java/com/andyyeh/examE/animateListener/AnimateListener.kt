package com.andyyeh.examE.animateListener

import android.view.animation.Animation

class AnimateListener : Animation.AnimationListener{

    private var mOnAnimationRepeat: onAnimationRepeat? = null
    private var mOnAnimationEnd: onAnimationEnd? = null
    private var mOnAnimationStart: onAnimationStart? = null

    fun setOnAnimationRepeat(onAnimationRepeat: onAnimationRepeat) : Animation.AnimationListener{
        this.mOnAnimationRepeat = onAnimationRepeat
        return this
    }

    fun setOnAnimationEnd(onAnimationEnd: onAnimationEnd) : Animation.AnimationListener{
        this.mOnAnimationEnd = onAnimationEnd
        return this
    }

    fun setOnAnimationStart(onAnimationStart: onAnimationStart) : Animation.AnimationListener{
        this.mOnAnimationStart = onAnimationStart
        return this
    }

    override fun onAnimationRepeat(p0: Animation?) {
        if (mOnAnimationRepeat != null) mOnAnimationRepeat!!.onAnimationRepeat(p0)
    }

    override fun onAnimationEnd(p0: Animation?) {
        if (mOnAnimationEnd != null) mOnAnimationEnd!!.onAnimationEnd(p0)
    }

    override fun onAnimationStart(p0: Animation?) {
        if (mOnAnimationStart != null) mOnAnimationStart!!.onAnimationStart(p0)
    }

    interface onAnimationRepeat{
        fun onAnimationRepeat(p0: Animation?)
    }

    interface onAnimationEnd{
        fun onAnimationEnd(p0: Animation?)
    }

    interface onAnimationStart{
        fun onAnimationStart(p0: Animation?)
    }
}