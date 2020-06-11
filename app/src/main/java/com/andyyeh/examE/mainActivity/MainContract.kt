package com.andyyeh.examE.mainActivity

import com.andyyeh.examE.mainActivity.model.UserBean
import io.reactivex.functions.Consumer

/**The contract to help unit test**/
interface MainContract {
    interface Repository {
        fun getDataFromInternet(since: Int, consumer: Consumer<ArrayList<UserBean>>)
    }
}