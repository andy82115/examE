package com.andyyeh.examE.userDetailActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.andyyeh.examE.Configuration

class TestLiveData(str: String) : MutableLiveData<String>(str) {

    private val TAG = TestLiveData::class.java.simpleName

    override fun onInactive() {
        super.onInactive()
        if (Configuration.DEBUG) Log.e("live data", "live data on inactive")
    }

    override fun onActive() {
        super.onActive()
        if (Configuration.DEBUG) Log.e("live data", "live data on active")

    }
}