package com.andyyeh.examE.animateListener

import android.animation.Animator

class AnimatorListener : Animator.AnimatorListener {

    private var mOnAnimationRepeat: onAnimationRepeat? = null
    private var mOnAnimationEnd: onAnimationEnd? = null
    private var mOnAnimationCancel: onAnimationCancel? = null
    private var mOnAnimationStart: onAnimationStart? = null


    fun setOnAnimationRepeat(onAnimationRepeat: onAnimationRepeat) : AnimatorListener {
        this.mOnAnimationRepeat = onAnimationRepeat
        return this
    }

    fun setOnAnimationEnd(onAnimationEnd: onAnimationEnd) : AnimatorListener {
        this.mOnAnimationEnd = onAnimationEnd
        return this
    }

    fun setOnAnimationCancel(onAnimationCancel: onAnimationCancel) : AnimatorListener {
        this.mOnAnimationCancel = onAnimationCancel
        return this
    }

    fun setOnAnimationStart(onAnimationStart: onAnimationStart) : AnimatorListener {
        this.mOnAnimationStart = onAnimationStart
        return this
    }



    override fun onAnimationRepeat(p0: Animator?) {
        if (mOnAnimationRepeat != null) mOnAnimationRepeat!!.onAnimationRepeat(p0)
    }

    override fun onAnimationEnd(p0: Animator?) {
        if (mOnAnimationEnd != null) mOnAnimationEnd!!.onAnimationEnd(p0)
    }

    override fun onAnimationCancel(p0: Animator?) {
        if (mOnAnimationCancel != null) mOnAnimationCancel!!.onAnimationCancel(p0)
    }

    override fun onAnimationStart(p0: Animator?) {
        if (mOnAnimationStart != null) mOnAnimationStart!!.onAnimationStart(p0)
    }

    interface onAnimationRepeat{
        fun onAnimationRepeat(p0: Animator?)
    }

    interface onAnimationEnd{
        fun onAnimationEnd(p0: Animator?)
    }

    interface onAnimationCancel{
        fun onAnimationCancel(p0: Animator?)
    }

    interface onAnimationStart{
        fun onAnimationStart(p0: Animator?)
    }
}