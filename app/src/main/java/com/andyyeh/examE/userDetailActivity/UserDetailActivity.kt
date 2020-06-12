package com.andyyeh.examE.userDetailActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.andyyeh.examE.Configuration
import com.andyyeh.examE.R
import com.andyyeh.examE.databinding.ActivityUserDetailBinding
import com.andyyeh.examE.mvvmBase.BaseActivity
import javax.inject.Inject

class UserDetailActivity : BaseActivity() {

    @Inject lateinit var mViewModel: UserDetailViewModel

    private lateinit var dataBinding : ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        dataBinding.userDetail = mViewModel

        requestDetailData()
    }

    private fun requestDetailData(){
        val loginId = intent.getStringExtra(Configuration.LOGIN_ID)
        mViewModel.requestDetailData(loginId)
    }
}
