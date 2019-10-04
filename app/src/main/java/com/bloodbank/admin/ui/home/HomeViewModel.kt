package com.bloodbank.ui.auth

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.bloodbank.admin.data.network.entities.Bank
import com.bloodbank.admin.data.sqlite.Config
import com.bloodbank.admin.ui.home.HomeListener
import com.bloodbank.admin.ui.home.bank.AddBankActivity
import com.bloodbank.admin.ui.home.bank.BankActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeViewModel(
    private val context: Context
) : ViewModel() {
    var homeListener: HomeListener? = null
    var bankUsername: String = ""
    var bankPassword: String = ""
    var bankName: String = ""
    var database = FirebaseDatabase.getInstance().reference.child(Config.FIREBASE_DATABASE_BANK)

    fun onAddbank(view: View){
        val intent = Intent(context, AddBankActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun onBanksClicked(view: View){
        val intent = Intent(context, BankActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun onBackClicked(view: View){
        homeListener?.onBackClicked()
    }

    fun OnRegisterBankClick(view: View){
        checkIfBankLoginExists()
    }

    fun regiterBank(){
        val id = database.push().key
        val bank = Bank(id, bankName, "", "", "", 0.0, 0.0, bankUsername, bankPassword )
        database.child(id.toString()).setValue(bank)
        homeListener?.onRegisterBankFinished()
    }

    fun checkIfBankLoginExists(){
        if(bankName.isNullOrEmpty() || bankUsername.isNullOrEmpty() || bankPassword.isNullOrEmpty()){
            homeListener?.onInputError("Invalid details")
            return
        }
        homeListener?.onBankLoginExistsCheckStarted()
        val bankListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(ds  in dataSnapshot.children) {
                    val bank = ds.getValue(Bank::class.java)
                    if (bank != null && bank.username.equals(bankUsername) && bank.password.equals(bankPassword)) {
                        homeListener?.onBankLoginExistsCheckFinished(true)
                        return
                    }
                }
                regiterBank()
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        database.addListenerForSingleValueEvent(bankListener)
    }
}