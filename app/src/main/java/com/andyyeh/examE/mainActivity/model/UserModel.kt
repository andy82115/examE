package com.andyyeh.examE.mainActivity.model

class UserModel {

    var datas = ArrayList<UserBean>()

    //FIXME: this is the fake data for UI Version
    init {
        val bean = UserBean("https://avatars0.githubusercontent.com/u/1?v=4", "hdd", false)
        val bean2 = UserBean("https://avatars0.githubusercontent.com/u/2?v=4", "ddd", true)
        datas.add(bean)
        datas.add(bean2)
    }

}