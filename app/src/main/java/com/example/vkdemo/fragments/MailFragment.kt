package com.example.vkdemo.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.vkdemo.R
import com.example.vkdemo.adapters.MailAdapter
import com.example.vkdemo.models.DialogUserModel
import com.example.vkdemo.presenters.MailPresenter
import com.example.vkdemo.views.MailView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

/**
 * A simple [Fragment] subclass.
 */
class MailFragment : MvpAppCompatFragment(), MailView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MailAdapter

    @InjectPresenter
    lateinit var presenter: MailPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_mail, container, false)
        viewAdapter = MailAdapter(getData())
        recyclerView = root.findViewById<RecyclerView>(R.id.rv_mail_messages).apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }
        return root
    }

    fun getData() : ArrayList<DialogUserModel> {
        return arrayListOf(DialogUserModel("first", "mes", "ph")
            , DialogUserModel("second", "message2", "ph2"))
    }

}
