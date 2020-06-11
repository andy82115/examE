package com.andyyeh.examE.mainActivity.model

class UserModel {

    var datas = ArrayList<UserBean>()

    init { }

    fun addData(beans: ArrayList<UserBean>){
        datas.addAll(beans)
    }
}