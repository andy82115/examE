package com.andyyeh.examE

import android.util.Log
import com.andyyeh.examE.mainActivity.model.UserBean
import com.example.gituserapi.NetUserBean

class BeanTransformer private constructor(){

    companion object {
        val instance = BeanTransformer()
    }

    fun netUserBean2UserBean(netUserBeans: List<NetUserBean>): ArrayList<UserBean>{
        val userBeans = ArrayList<UserBean>()
        for (netUserBean in netUserBeans){
            val userBean = UserBean(
                netUserBean.id,
                netUserBean.avatar_url,
                netUserBean.login,
                netUserBean.site_admin)
            userBeans.add(userBean)
        }
        return userBeans
    }
}