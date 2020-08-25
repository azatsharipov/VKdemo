package com.example.vkdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vkdemo.R
import com.example.vkdemo.models.DialogUserModel

class MailAdapter(private val dialogs: ArrayList<DialogUserModel>) :
    RecyclerView.Adapter<MailAdapter.MyViewHolder>() {
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById(R.id.tv_mail_dialog_name)
        var tvMessage: TextView = view.findViewById(R.id.tv_mail_dialog_message)
        var ivPhoto: ImageView = view.findViewById(R.id.iv_mail_dialog_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dialog, parent, false) as View
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dialogs.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = dialogs[position].name
        holder.tvMessage.text = dialogs[position].message
    }
}