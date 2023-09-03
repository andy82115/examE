package com.andyyeh.examE.mainActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andyyeh.examE.Configuration
import com.andyyeh.examE.R
import com.andyyeh.examE.mainActivity.adapter.UserAdapter
import com.andyyeh.examE.mvvmBase.BaseActivity
import com.andyyeh.examE.userDetailActivity.UserDetailActivity
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

        this.setRecyclerView()
        this.requestData()
    }

    private fun setRecyclerView(){
        detectRecyclerScroll()
        detectItemClicked()
        vUserInfoRV.layoutManager = LinearLayoutManager(this)
        vUserInfoRV.adapter = mAdapter
    }

    /**
     * the callback is login id
     * **/
    private fun detectItemClicked(){
        mAdapter.itemClickedObserver = Consumer {
            val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity).toBundle()
            val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
            intent.putExtra(Configuration.LOGIN_ID, it)
            startActivity(intent, bundle)
        }
    }

    /**
     * @see RecyclerView.OnScrollListener.onScrolled locate item position
     * @see RecyclerView.OnScrollListener.onScrollStateChanged detect if touched the Bottom
     * **/
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
                mAdapter.submitList(it)
            },
            Action {
                Toast.makeText(this@MainActivity, "size reach 100", Toast.LENGTH_LONG).show()
            })
    }
}
