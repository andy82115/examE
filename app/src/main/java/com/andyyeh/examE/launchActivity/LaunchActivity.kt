package com.andyyeh.examE.launchActivity

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import com.andyyeh.examE.R
import com.andyyeh.examE.animateListener.AnimatorListener
import com.andyyeh.examE.databinding.ActivityLaunchBinding
import com.andyyeh.examE.mainActivity.MainActivity
import com.andyyeh.examE.mvvmBase.BaseActivity
import kotlinx.android.synthetic.main.activity_launch.*
import javax.inject.Inject
import kotlin.math.hypot

class LaunchActivity : BaseActivity() {

    @Inject lateinit var mViewModel: LaunchActivityViewModel

    private lateinit var dataBinding: ActivityLaunchBinding

    val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Doing data binding
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_launch)
        dataBinding.launchActivityViewModel = mViewModel

        //Show Animation
        this.startLaunchAnimate()
        this.twinkleInfoText()
        this.containerOnTouched()
    }

    private fun containerOnTouched(){
        vContainerRL.setOnTouchListener(object : View.OnTouchListener{
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if (mViewModel.vInfoTextVisible.get() == View.GONE) return false
                this@LaunchActivity.goNextPage(p1!!.x.toInt(), p1.y.toInt())
                return false
            }

        })
    }

    private fun goNextPage(touchedX: Int, touchedY: Int){
        val endRadius = hypot(vContainerRL.height.toDouble(), vContainerRL.width.toDouble()).toFloat()

        val anim = ViewAnimationUtils.createCircularReveal(vContainerRL, touchedX, touchedY, endRadius, 0f)
        anim.addListener(AnimatorListener().setOnAnimationEnd(object : AnimatorListener.onAnimationEnd{
            override fun onAnimationEnd(p0: Animator?) {
                vContainerRL.visibility = View.GONE
                vContainerRL.postDelayed({
                    val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this@LaunchActivity).toBundle()
                    val intent = Intent(this@LaunchActivity, MainActivity::class.java)
                    startActivity(intent, bundle)
                },500)
                vContainerRL.postDelayed({this@LaunchActivity.finish()}, 2000)
            }

        }))
        anim.setDuration(1000).interpolator = AccelerateInterpolator()
        anim.start()
    }

    private fun startLaunchAnimate() {
        vLogoAnimateLAV.addAnimatorListener(
            AnimatorListener()
            .setOnAnimationEnd(object : AnimatorListener.onAnimationEnd {
            override fun onAnimationEnd(p0: Animator?) {
                mViewModel.vInfoTextVisible.set(View.VISIBLE)
            }
        }))
        vLogoAnimateLAV.playAnimation()
    }

    private fun twinkleInfoText(){
        mHandler.postDelayed(object : Runnable {
            override fun run() {
                mViewModel.twinkleEvent()
                if (mViewModel.isStopTwinkle) mHandler.removeCallbacks(this)
                mHandler.postDelayed(this, mViewModel.TWINKLE_TIME)
            }
        }, mViewModel.TWINKLE_TIME)
    }
}
