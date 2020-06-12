package com.andyyeh.examE

import com.andyyeh.examE.mainActivity.model.UserBean
import com.andyyeh.examE.userDetailActivity.UserDetailBean
import com.example.gituserapi.beans.NetUserBean
import com.example.gituserapi.beans.NetUserDetailBean

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

    /***
     * @param netUserDetailBean present the online data
     * @return the Local data
     * @see UserDetailBean
     * */
    fun netUserDetailBean2UserDetailBean(netUserDetailBean: NetUserDetailBean): UserDetailBean{
        return UserDetailBean(
            netUserDetailBean.avatar_url,
            netUserDetailBean.name,
            netUserDetailBean.bio,
            netUserDetailBean.login,
            netUserDetailBean.site_admin,
            netUserDetailBean.location,
            netUserDetailBean.blog)
    }
}