package com.andyyeh.examE.mainActivity

import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import com.andyyeh.examE.R
import com.andyyeh.examE.mainActivity.adapter.UserAdapter
import com.andyyeh.examE.mvvmBase.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var mViewModel: MainActivityViewModel
    @Inject lateinit var mAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setTransition()
        this.setRecyclerView()
    }

    private fun setTransition(){
        window.enterTransition = Slide(Gravity.END).setDuration(250)
        window.returnTransition = Slide(Gravity.START).setDuration(250)
    }

    private fun setRecyclerView(){
        vUserInfoRV.layoutManager = LinearLayoutManager(this)
        vUserInfoRV.adapter = mAdapter
    }
}
