package com.hiloislay.passmanagerkt.controller.activity

import android.content.Intent

class MainActivity : BaseActivity() {

    override fun onResume() {
        super.onResume()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
