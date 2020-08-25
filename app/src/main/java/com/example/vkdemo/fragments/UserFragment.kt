package com.example.vkdemo.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController

import com.example.vkdemo.R
import com.example.vkdemo.models.UserModel
import com.example.vkdemo.presenters.UserPresenter
import com.example.vkdemo.views.UserView
import com.squareup.picasso.Picasso
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

/**
 * A simple [Fragment] subclass.
 */
class UserFragment : MvpAppCompatFragment(), UserView {
    lateinit var etId: EditText
    lateinit var btShow: Button
    lateinit var tvName: TextView
    lateinit var ivPhoto: ImageView
    lateinit var pbLoading: ProgressBar
    lateinit var btToMail: Button

    @InjectPresenter
    lateinit var presenter: UserPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_user, container, false)
        etId = root.findViewById(R.id.et_user_id)
        tvName = root.findViewById(R.id.tv_user_name)
        btShow = root.findViewById(R.id.bt_user_show)
        ivPhoto = root.findViewById(R.id.iv_user_photo)
        pbLoading = root.findViewById(R.id.pb_user_loading)
        btToMail = root.findViewById(R.id.bt_user_to_mail)
        btShow.setOnClickListener {
            presenter.getUser(etId.text.toString())
        }
        btToMail.setOnClickListener {
            findNavController().navigate(R.id.mailFragment)
        }
        return root
    }

    override fun startRequest() {
        pbLoading.visibility = View.VISIBLE
    }

    override fun stopRequest() {
        pbLoading.visibility = View.GONE
    }

    override fun showError(resourceId: Int) {
    }

    override fun showUser(user: UserModel?) {
        tvName.text = user?.firstName
        user?.photo?.let { Picasso.get().load(user.photo).into(ivPhoto) }
    }

}
