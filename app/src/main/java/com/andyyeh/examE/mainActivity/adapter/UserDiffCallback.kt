package com.andyyeh.examE.mainActivity.adapter

import androidx.recyclerview.widget.DiffUtil
import com.andyyeh.examE.mainActivity.model.UserBean

class UserDiffCallback : DiffUtil.ItemCallback<UserBean>() {


    override fun areItemsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
        if (oldItem.userName != newItem.userName){
            return false
        }
        return true
    }


}
