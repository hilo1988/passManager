package com.hiloislay.passmanagerkt.controller.activity

import android.app.Activity
import android.os.Bundle
import com.hiloislay.passmanagerkt.util.Logger

/**
 * 基底アクティビティ
 */
open class BaseActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        com.hiloislay.passmanagerkt.util.Logger.v()
    }

    override fun onStart() {
        super.onStart()
        com.hiloislay.passmanagerkt.util.Logger.v()
    }

    override fun onRestart() {
        super.onRestart()
        com.hiloislay.passmanagerkt.util.Logger.v()
    }

    override fun onResume() {
        super.onResume()
        com.hiloislay.passmanagerkt.util.Logger.v()
    }

    override fun onPause() {
        super.onPause()
        com.hiloislay.passmanagerkt.util.Logger.v()
    }

    override fun onStop() {
        super.onStop()
        com.hiloislay.passmanagerkt.util.Logger.v()
    }

    override fun finish() {
        super.finish()
        com.hiloislay.passmanagerkt.util.Logger.v()
    }

    override fun onDestroy() {
        super.onDestroy()
        com.hiloislay.passmanagerkt.util.Logger.v()
    }
}