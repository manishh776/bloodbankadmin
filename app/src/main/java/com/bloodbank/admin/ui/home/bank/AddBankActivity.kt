package com.bloodbank.admin.ui.home.bank

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bloodbank.admin.R
import com.bloodbank.admin.databinding.ActivityAddBankBinding
import com.bloodbank.admin.databinding.ActivityBankBinding
import com.bloodbank.admin.ui.home.HomeListener
import com.bloodbank.ui.auth.HomeViewModel
import com.bloodbank.ui.auth.HomeViewModelFactory
import com.bloodbank.utils.snackbar
import com.bloodbank.utils.toast
import kotlinx.android.synthetic.main.activity_add_bank.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AddBankActivity : AppCompatActivity(), KodeinAware, HomeListener {
    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()
    private var progressDialog : ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAddBankBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_add_bank)
        val viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.homeListener = this
        progressDialog = ProgressDialog(this)
    }

    override fun onRegisterBankStarted() {
        showProgressDialog()
    }

    fun showProgressDialog() {
        progressDialog!!.setMessage("Please Wait...")
        progressDialog!!.show()
    }

    fun hideProgressDialog(){
        progressDialog!!.dismiss()
    }
    override fun onRegisterBankFinished() {
        hideProgressDialog()
        toast("bank registered")
        finish()
    }

    override fun onInputError(message: String) {
        toast(message)
    }

    override fun onBackClicked() {
        finish()
    }

    override fun onBankLoginExistsCheckStarted() {
        showProgressDialog()
    }

    override fun onBankLoginExistsCheckFinished(exists: Boolean) {
        if(exists){
            hideProgressDialog()
            toast("this login already exists")
        }
    }
}
