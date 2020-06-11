package com.andyyeh.examE.mainActivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.andyyeh.examE.R
import com.andyyeh.examE.databinding.ItemUserInfoBinding
import com.andyyeh.examE.mainActivity.model.UserBean
import com.bumptech.glide.Glide

class UserAdapter(datas: ArrayList<UserBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val mData = datas

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemUserInfoBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user_info, parent, false)

        return UserViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return this.mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding: ItemUserInfoBinding? = DataBindingUtil.getBinding(holder.itemView)
        binding!!.userBean = this.mData[position]
        binding.executePendingBindings()
    }
}

@BindingAdapter("imgUrl")
fun loadImg(view: ImageView, url: String){
    Glide.with(view).load(url).into(view)
}