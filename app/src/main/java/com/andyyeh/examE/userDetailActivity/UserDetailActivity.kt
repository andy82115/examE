package com.andyyeh.examE.userDetailActivity

import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andyyeh.examE.Configuration
import com.andyyeh.examE.R
import com.andyyeh.examE.databinding.ActivityUserDetailBinding
import com.andyyeh.examE.mvvmBase.BaseActivity
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.coroutines.*
import javax.inject.Inject

class UserDetailActivity : BaseActivity() {

//    @Inject lateinit var mViewModel: UserDetailViewModel

    @Inject lateinit var mRepository: UserDetailRepository

    private lateinit var mViewModel: UserDetailViewModel
    private lateinit var dataBinding : ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        mViewModel = ViewModelProvider(this, UserDetailViewModelFactory(mRepository)).get(UserDetailViewModel::class.java)
        dataBinding.userDetail = mViewModel

        setTransition()
        requestDetailData()
        onCLickedListener()
        linkOnClicked()
    }

    private fun linkOnClicked(){
        mViewModel.testLiveData.observe(this, Observer<String>{
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        vLinkLL.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                delay(3000)
                mViewModel.testLiveData.value = "link on clicked"
            }
        }
    }

    private fun setTransition(){
        window.enterTransition = Slide(Gravity.END).setDuration(250)
        window.returnTransition = Slide(Gravity.START).setDuration(250)
    }

    /**
     * request data from online, base on loginId
     * **/
    private fun requestDetailData(){
        val loginId = intent.getStringExtra(Configuration.LOGIN_ID)
        mViewModel.requestDetailData(loginId!!)
    }

    /**
     * NOTICE: i did not make it with DataBinding
     * and the reason is that I thought ViewModel
     * should be clean enough to do the Unit Test.
     *
     * Additionally, the activity will control all
     * the UI Flow ~
     * **/
    private fun onCLickedListener(){
        vDetailReturnIV.setOnClickListener { this@UserDetailActivity.finishAfterTransition() }
    }
}
