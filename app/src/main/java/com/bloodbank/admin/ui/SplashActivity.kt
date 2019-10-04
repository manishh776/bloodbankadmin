package com.bloodbank.admin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bloodbank.admin.data.sqlite.Config
import com.bloodbank.admin.data.sqlite.KeyValueDb
import com.bloodbank.admin.ui.auth.LoginActivity
import com.bloodbank.admin.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val login_state = KeyValueDb.get(this, Config.LOGIN_STATE,"0").toInt()
        if(login_state == 1){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
