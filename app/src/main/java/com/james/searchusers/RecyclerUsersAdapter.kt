package com.james.searchusers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.james.searchusers.model.Item
import kotlinx.android.synthetic.main.item_users.view.*

class RecyclerUsersAdapter(var context: Context) : PagedListAdapter<Item, RecyclerUsersAdapter.UserHolder>
    (object : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.login == newItem.login

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerUsersAdapter.UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return UserHolder(view)
    }


    inner class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txName: TextView = view.txName
        var imageAvatar: ImageView = view.imageAvatar
        fun bindUser(user: Item) {
            txName.text = user.login

            Glide.with(context)//使用Glide套件取得照片
                .load(user.avatar_url)
                .override(300)
                .into(imageAvatar)
        }

    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) = holder.bindUser(getItem(position)!!)


}