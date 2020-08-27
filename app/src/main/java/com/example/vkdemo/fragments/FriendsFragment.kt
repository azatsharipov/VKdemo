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
import com.example.vkdemo.adapters.FriendsAdapter
import com.example.vkdemo.models.UserModel
import com.example.vkdemo.presenters.FriendsPresenter
import com.example.vkdemo.views.FriendsView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

/**
 * A simple [Fragment] subclass.
 */
class FriendsFragment : MvpAppCompatFragment(), FriendsView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: FriendsAdapter
    private lateinit var pbLoading: ProgressBar

    @InjectPresenter
    lateinit var presenter: FriendsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_friends, container, false)
        pbLoading = root.findViewById(R.id.pb_friends_loading)
        recyclerView = root.findViewById(R.id.rv_friends)
        presenter.getFriends()
        return root
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
        viewAdapter = FriendsAdapter(friends)
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }
    }

}
