package com.james.searchusers.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.james.searchusers.model.Item
import com.james.searchusers.repository.Repository

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    val TAG = UsersViewModel::class.java.simpleName
    var repository = Repository()

    fun getUser(keyWord: String): LiveData<PagedList<Item>> {
        return repository.getUsers(keyWord)
    }
}