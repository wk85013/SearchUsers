package com.james.searchusers.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.james.searchusers.ApiService
import com.james.searchusers.RetrofitManager
import com.james.searchusers.model.Item
import com.james.searchusers.model.Users
import com.james.searchusers.paging.UsersSourceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository() {
    val TAG = Repository::class.java.simpleName
    var myAPIService: ApiService? = RetrofitManager.client.create(
        ApiService::class.java)
    var liveData = MutableLiveData<Users>()



    fun getUsers(keyWord: String, page: Int, perPage: Int) {
        // 建立連線
        val call: Call<Users> = myAPIService!!.getUser(keyWord, page, perPage)
        Log.i(TAG, " call.request().url(): ${call.request().url()}")

        // 建立連線
        call.enqueue(object : Callback<Users?> {
            override fun onResponse(call: Call<Users?>?, response: Response<Users?>?) {

                Log.i(TAG, "response.isSuccessful: ${response!!.isSuccessful}")
                Log.i(TAG, "response!!.code(): ${response!!.code()}")
                Log.i(TAG, "response!!.errorBody(): ${response!!.errorBody()}")
                Log.i(TAG, "response!!.body(): ${response!!.body()}")
                val responseCode = response.code()
                if (responseCode == 200) {
                    val body = response.body()
                    liveData.postValue(body)


                } else {
                    liveData.postValue(null)

                }
            }

            override fun onFailure(call: Call<Users?>?, t: Throwable?) {

            }
        })

    }

    fun getUsers(keyWord: String): LiveData<PagedList<Item>> {
         val factory = UsersSourceFactory(keyWord)
         val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setInitialLoadSizeHint(30 * 2)
            .setEnablePlaceholders(false)
            .build()
        return LivePagedListBuilder<Int, Item>(factory, config)
            .setInitialLoadKey(0)
            .build()

    }


}
