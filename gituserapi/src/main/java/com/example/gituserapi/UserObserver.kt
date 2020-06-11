package com.example.andynovelapi.internetApi

import android.util.Log
import com.example.climateapi.Configuration
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class UserObserver<T> (listenerOnNext: UserObserverListener.ObserverOnNextListener<T>) : Observer<T>, UserObserverListener.ProgressCancelListener {

    private val TAG = UserObserver::class.java.simpleName

    private val DEBUG = Configuration.DEBUG

    private var mListenerOnNext = listenerOnNext
    private var mListenerOnError: UserObserverListener.ObserverOnErrorListener? = null

    private var disposable: Disposable? = null

    constructor(listenerOnNext: UserObserverListener.ObserverOnNextListener<T>,
                listenerOnError: UserObserverListener.ObserverOnErrorListener) : this(listenerOnNext){
        this.mListenerOnError = listenerOnError
    }

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        this.disposable = d
        if (DEBUG) Log.e(TAG, "OnSubscribe")
    }

    override fun onNext(t: T) {
        mListenerOnNext.onNext(t)
        if (DEBUG) Log.e(TAG, "OnNext = $t")
    }

    override fun onError(e: Throwable) {
        if (mListenerOnError != null) mListenerOnError!!.onError()
        if (DEBUG) Log.e(TAG, "OnError = $e")
    }

    override fun onCancelProgress() {
        if (disposable!= null && !disposable!!.isDisposed) disposable!!.dispose()
        if (DEBUG) Log.e(TAG, "OnError")
    }
}