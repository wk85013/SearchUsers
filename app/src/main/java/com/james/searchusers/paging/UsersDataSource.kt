package com.james.searchusers.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.james.searchusers.ApiService
import com.james.searchusers.RetrofitManager
import com.james.searchusers.model.Item
import com.james.searchusers.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersDataSource(val keyWord: String) : PageKeyedDataSource<Int, Item>() {

    val TAG = UsersDataSource::class.java.simpleName
    var myAPIService: ApiService? = RetrofitManager.client.create(ApiService::class.java)
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Item>) {
        // 建立連線
        val call: Call<Users> = myAPIService!!.getUser(keyWord, 1, 30)
        Log.i(TAG, " call.request().url(): ${call.request().url()}")

        // 執行連線
        call.enqueue(object : Callback<Users?> {
            override fun onResponse(call: Call<Users?>?, response: Response<Users?>?) {

                Log.i(TAG, "response.isSuccessful: ${response!!.isSuccessful}")
                Log.i(TAG, "response!!.code(): ${response!!.code()}")
                Log.i(TAG, "response!!.errorBody(): ${response!!.errorBody()}")
                Log.i(TAG, "response!!.body(): ${response!!.body()}")
                val responseCode = response.code()
                if (responseCode == 200) {
                    val body = response.body()!!.items
                    callback.onResult(body, null, 2)

                }
            }

            override fun onFailure(call: Call<Users?>?, t: Throwable?) {

            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        // 建立連線
        val call: Call<Users> = myAPIService!!.getUser(keyWord, params.key, 30)
        Log.i(TAG, " call.request().url(): ${call.request().url()}")

        // 執行連線
        call.enqueue(object : Callback<Users?> {
            override fun onResponse(call: Call<Users?>?, response: Response<Users?>?) {

                Log.i(TAG, "response.isSuccessful: ${response!!.isSuccessful}")
                Log.i(TAG, "response!!.code(): ${response!!.code()}")
                Log.i(TAG, "response!!.errorBody(): ${response!!.errorBody()}")
                Log.i(TAG, "response!!.body(): ${response!!.body()}")
                val responseCode = response.code()
                if (responseCode == 200) {
                    val body = response.body()!!.items
                    callback.onResult(body, params.key + 1)

                }
            }

            override fun onFailure(call: Call<Users?>?, t: Throwable?) {

            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
    }
}