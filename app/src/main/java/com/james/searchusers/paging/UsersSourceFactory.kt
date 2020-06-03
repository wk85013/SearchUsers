package com.james.searchusers.paging

import androidx.paging.DataSource
import com.james.searchusers.model.Item


class UsersSourceFactory(val keyWord: String) : DataSource.Factory<Int, Item>() {

    var user: UsersDataSource? = null
    override fun create(): DataSource<Int, Item> {
        user = UsersDataSource(keyWord)
        return user as UsersDataSource
    }

}