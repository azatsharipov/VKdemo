package com.example.vkdemo.fragments


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.vkdemo.R
import com.example.vkdemo.presenters.LoginPresenter
import com.example.vkdemo.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.utils.VKUtils
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : MvpAppCompatFragment(), LoginView {
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var btLogin: Button
    lateinit var pbLoading: ProgressBar

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        etEmail = root.findViewById(R.id.et_login_email)
        etPassword = root.findViewById(R.id.et_login_password)
        btLogin = root.findViewById(R.id.bt_login_login)
        pbLoading = root.findViewById(R.id.pb_login_loading)

        btLogin.setOnClickListener {
            VK.login(activity as Activity, arrayListOf(VKScope.WALL, VKScope.PHOTOS))
        }
//        val fingerprints: Array<String?>? =
//            activity?.getPackageName()?.let { VKUtils.getCertificateFingerprint(activity, it) }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!presenter.login(requestCode = requestCode, resultCode = resultCode, data = data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
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

    override fun openMenu() {
        Toast.makeText(activity, "ok login", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.userFragment)
    }

    override fun saveUserId(userId: String) {
        val pref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (pref.edit()) {
            putString("ID", userId)
            commit()
        }
    }


}
