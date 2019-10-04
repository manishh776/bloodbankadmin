package com.bloodbank.admin.ui.home

interface HomeListener {
    fun onRegisterBankStarted()
    fun onRegisterBankFinished()
    fun onInputError(message: String)
    fun onBackClicked()
    fun onBankLoginExistsCheckStarted()
    fun onBankLoginExistsCheckFinished(exists: Boolean)
}