package com.andyyeh.examE.userDetailActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andyyeh.examE.di.BasicInfo

@Suppress("UNCHECKED_CAST")
class UserDetailViewModelFactory (private var repository: UserDetailRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserDetailViewModel(repository) as T
    }
}