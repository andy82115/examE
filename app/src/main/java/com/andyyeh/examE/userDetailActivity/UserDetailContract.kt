package com.andyyeh.examE.userDetailActivity

import io.reactivex.functions.Consumer


interface UserDetailContract {
    interface Repository{
        fun getUserDetailFromInternet(login: String, consumer: Consumer<UserDetailBean>)
    }
}