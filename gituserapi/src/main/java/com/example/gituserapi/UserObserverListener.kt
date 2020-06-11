package com.example.andynovelapi.internetApi

interface UserObserverListener{

    interface ObserverOnNextListener<T>{
        fun onNext(t: T)
    }

    interface ObserverOnErrorListener{
        fun onError()
    }

    interface ProgressCancelListener{
        fun onCancelProgress()
    }
}