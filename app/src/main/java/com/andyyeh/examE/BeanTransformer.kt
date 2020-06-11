package com.andyyeh.examE

import com.andyyeh.examE.mainActivity.model.UserBean
import com.example.gituserapi.beans.NetUserBean

/**the bean convert center**/
class BeanTransformer private constructor(){

    companion object {
        val instance = BeanTransformer()
    }

    /**
     * @param netUserBeans present the online data
     * @return the Local data
     * @see UserBean
     * **/
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