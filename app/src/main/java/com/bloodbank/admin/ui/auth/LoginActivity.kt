package com.bloodbank.admin.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bloodbank.admin.R
import com.bloodbank.admin.data.sqlite.Config
import com.bloodbank.admin.data.sqlite.KeyValueDb
import com.bloodbank.admin.databinding.ActivityLoginBinding
import com.bloodbank.admin.ui.home.HomeActivity
import com.bloodbank.ui.auth.AuthListener
import com.bloodbank.ui.auth.AuthViewModel
import com.bloodbank.ui.auth.AuthViewModelFactory
import com.bloodbank.utils.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware, AuthListener {
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()
    private var progressDialog : ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        progressDialog = ProgressDialog(this)
        bottom_arc.setTargetView(top_image)
    }

    override fun onLoginSuccess() {
        progressDialog?.dismiss()
        root_layout.snackbar("Login Success")
        gotoHomeActivity()
    }

    private fun gotoHomeActivity() {
        KeyValueDb.set(this, Config.LOGIN_STATE, "1",1)
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onLoginFailed() {
        progressDialog?.dismiss()
        root_layout.snackbar("Login failure")
    }

    override fun onLoginStarted() {
        progressDialog?.setMessage("Loggin in ...")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }

    override fun onInputError(message: String) {
        root_layout.snackbar(message)
    }

}
