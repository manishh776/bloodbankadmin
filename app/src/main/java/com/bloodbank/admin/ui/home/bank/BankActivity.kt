package com.bloodbank.admin.ui.home.bank

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bloodbank.admin.R
import com.bloodbank.admin.databinding.ActivityBankBinding
import com.bloodbank.admin.databinding.ActivityLoginBinding
import com.bloodbank.admin.ui.home.HomeListener
import com.bloodbank.ui.auth.AuthViewModel
import com.bloodbank.ui.auth.AuthViewModelFactory
import com.bloodbank.ui.auth.HomeViewModel
import com.bloodbank.ui.auth.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class BankActivity : AppCompatActivity(), KodeinAware, HomeListener{
    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()
    private var progressDialog : ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityBankBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_bank)
        val viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.homeListener = this
        progressDialog = ProgressDialog(this)
    }

    override fun onRegisterBankStarted() {
    }

    override fun onRegisterBankFinished() {
    }

    override fun onInputError(message: String) {
    }

    override fun onBackClicked() {
        finish()
    }

    override fun onBankLoginExistsCheckStarted() {
    }

    override fun onBankLoginExistsCheckFinished(exists: Boolean) {
    }
}
