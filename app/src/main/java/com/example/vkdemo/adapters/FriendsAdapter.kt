package com.example.vkdemo.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vkdemo.R
import com.example.vkdemo.models.UserModel
import com.squareup.picasso.Picasso

class FriendsAdapter(private val friends: ArrayList<UserModel>) :
    RecyclerView.Adapter<FriendsAdapter.MyViewHolder>() {
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById(R.id.tv_friend_name)
        var ivPhoto: ImageView = view.findViewById(R.id.iv_friend_photo)
        fun setListener(id: String?) {
            view.setOnClickListener{ v ->
                val bundle = Bundle()
                bundle.putString("ID", id)
                v.findNavController().navigate(R.id.userFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friend, parent, false) as View
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setListener(friends[position].id)
        holder.tvName.text = friends[position].firstName + ' ' + friends[position].lastName
        Picasso.get().load(friends[position].photo).into(holder.ivPhoto)
    }
}