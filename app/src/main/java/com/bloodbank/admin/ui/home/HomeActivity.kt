package com.bloodbank.admin.ui.home

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bloodbank.admin.R
import com.bloodbank.admin.databinding.ActivityHomeBinding
import com.bloodbank.admin.databinding.ActivityLoginBinding
import com.bloodbank.ui.auth.*
import com.bloodbank.utils.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity() , KodeinAware {
    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()
    private var progressDialog : ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_home
        )
        val viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        binding.viewmodel = viewModel
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Please Wait...")
        progressDialog!!.setCancelable(false)
    }
}
