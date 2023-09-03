package com.andyyeh.examE.mainActivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andyyeh.examE.R
import com.andyyeh.examE.databinding.ItemUserInfoBinding
import com.andyyeh.examE.mainActivity.model.UserBean
import com.bumptech.glide.Glide
import io.reactivex.Single
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**Utilize Data binding to do it
 * @see R.layout.item_user_info
 * @param datas is the resource for binding data
 * **/
class UserAdapter(datas: MutableList<UserBean>, diffCallback: DiffUtil.ItemCallback<UserBean>) :
    ListAdapter<UserBean, RecyclerView.ViewHolder>(diffCallback) {

    @Inject lateinit var mAppContext: Context

//    private val mData = datas

    var itemClickedObserver: Consumer<String>? = null

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /**Inflate view with data binding
     * @see ItemUserInfoBinding
     * **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemUserInfoBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user_info, parent, false)

        return UserViewHolder(binding.root)
    }

//    override fun getItemCount(): Int {
//        return this.mData.size
//    }

    /**
     * binding each data to the
     * @see R.layout.item_user_info
     * **/
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding: ItemUserInfoBinding? = DataBindingUtil.getBinding(holder.itemView)
        binding!!.userBean = getItem(position)
        binding.root.setOnClickListener {
            if (itemClickedObserver == null) {return@setOnClickListener}
            Single.just(getItem(position).userName).subscribe(itemClickedObserver)
        }
        binding.executePendingBindings()
    }

//    fun setData(list: List<UserBean>){
//        var result: DiffUtil.DiffResult = DiffUtil.calculateDiff(UserDiffCallback(mData, list), true)
//
//        result.dispatchUpdatesTo(this)
//        mData.addAll(list)
//    }
}

/**
 * this func provide for
 * @see R.id.vUserImage
 * **/
@BindingAdapter("imgUrl")
fun loadImg(view: ImageView, url: String){
    Glide.with(view).load(url).into(view)
}