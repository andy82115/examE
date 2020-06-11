package com.andyyeh.examE.mainActivity

import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andyyeh.examE.R
import com.andyyeh.examE.mainActivity.adapter.UserAdapter
import com.andyyeh.examE.mvvmBase.BaseActivity
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
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
        this.requestData()
    }

    private fun setTransition(){
        window.enterTransition = Slide(Gravity.END).setDuration(250)
        window.returnTransition = Slide(Gravity.START).setDuration(250)
    }

    private fun setRecyclerView(){
        detectRecyclerScroll()
        vUserInfoRV.layoutManager = LinearLayoutManager(this)
        vUserInfoRV.adapter = mAdapter
    }

    private fun detectRecyclerScroll(){
        vUserInfoRV.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            var lastVisibleItem: Int? = 0
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem!! + 1 == mAdapter.itemCount){
                    this@MainActivity.requestData()
                }
            }
        })
    }

    /**
     * @see MainActivityViewModel.requestUserData
     * the it[0] present the start position
     * the it[1] present the size
     * **/
    private fun requestData(){
        mViewModel.requestUserData(
            Consumer {
                mAdapter.notifyItemRangeInserted(it[0], it[1])
            },
            Action {
                Toast.makeText(this@MainActivity, "size reach 100", Toast.LENGTH_LONG).show()
            })
    }
}
