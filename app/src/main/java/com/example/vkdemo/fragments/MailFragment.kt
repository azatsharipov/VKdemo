package com.example.vkdemo.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.example.vkdemo.R
import com.example.vkdemo.adapters.MailAdapter
import com.example.vkdemo.models.UserModel
import com.example.vkdemo.presenters.MailPresenter
import com.example.vkdemo.views.MailView
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

/**
 * A simple [Fragment] subclass.
 */
class MailFragment : MvpAppCompatFragment(), MailView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MailAdapter
    private lateinit var pbLoading: ProgressBar

    @InjectPresenter
    lateinit var presenter: MailPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_mail, container, false)
        pbLoading = root.findViewById(R.id.pb_mail_loading)
        recyclerView = root.findViewById(R.id.rv_mail_messages)
        presenter.getFriends()
        return root
    }

    fun getData() : ArrayList<UserModel> {
        return arrayListOf(UserModel("first", "ph")
            , UserModel("second", "ph2"))
    }

    override fun startRequest() {
        pbLoading.visibility = View.VISIBLE
    }

    override fun stopRequest() {
        pbLoading.visibility = View.GONE
    }

    override fun showError(resourceId: Int) {
        Toast.makeText(activity, getString(resourceId), Toast.LENGTH_SHORT).show()
    }

    override fun showFriends(friends: ArrayList<UserModel>) {
//        friends.add(UserModel("Azat", null))
        viewAdapter = MailAdapter(friends)
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }
    }

}
