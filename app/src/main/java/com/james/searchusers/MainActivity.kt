package com.james.searchusers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.james.searchusers.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.simpleName
    var usersViewModel: UsersViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ini()
        setListener()

    }

    private fun ini() {
        usersViewModel = ViewModelProviders.of(this@MainActivity).get(UsersViewModel::class.java)
    }

    private fun setListener() {
        recyclerUsersList.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerUsersList.setHasFixedSize(true)
        val adapter = RecyclerUsersAdapter(this@MainActivity)
        recyclerUsersList.adapter = adapter

        btnSearch.setOnClickListener {
            val users = usersViewModel!!.getUser(editTextUserName.text.toString())
            users.observe(this, Observer {
                adapter.submitList(it)
            })
        }
    }
}