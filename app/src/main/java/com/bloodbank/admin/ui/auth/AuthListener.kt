package com.bloodbank.ui.auth

interface AuthListener{
    fun onInputError(message : String)
    fun onLoginSuccess()
    fun onLoginFailed()
    fun onLoginStarted()
}