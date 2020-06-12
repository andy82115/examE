package com.andyyeh.examE.userDetailActivity

import io.reactivex.functions.Consumer

/**The contract to help unit test**/
interface UserDetailContract {
    interface Repository{
        fun getUserDetailFromInternet(login: String, consumer: Consumer<UserDetailBean>)
    }
}