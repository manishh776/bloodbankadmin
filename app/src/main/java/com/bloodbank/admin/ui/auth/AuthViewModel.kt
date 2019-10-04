package com.bloodbank.ui.auth

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.bloodbank.admin.data.sqlite.Config
import com.bloodbank.admin.data.sqlite.KeyValueDb
import com.bloodbank.admin.data.network.entities.Admin
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AuthViewModel(
    private val context: Context
) : ViewModel() {
    var authListener: AuthListener? = null
    var username: String = ""
    var password: String = ""
    var database = FirebaseDatabase.getInstance().reference.child(Config.FIREBASE_DATABASE_ADMIN)


    fun loginAdmin(view: View) {
        if (username.isEmpty() || password.isEmpty()) {
            authListener?.onInputError("Invalid details")
            return
        }
        authListener?.onLoginStarted()
        val adminListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(ds in dataSnapshot.children) {
                    val admin = ds.getValue(Admin::class.java)
                    if (admin!=null && admin.username.equals(username) && admin.password.equals(password)){
                        authListener?.onLoginSuccess()
                        updateToken()
                        return
                    }
                }
                authListener?.onLoginFailed()
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        database.addListenerForSingleValueEvent(adminListener)
    }

    private fun updateToken() {
        val token = KeyValueDb.get(context, Config.TOKEN, "")
        database.child("0").child("token").setValue(token)
    }
}