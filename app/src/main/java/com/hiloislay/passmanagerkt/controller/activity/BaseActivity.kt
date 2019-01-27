package com.yoidukigembu.passmanagerkt.controller.activity

import android.app.Activity
import android.os.Bundle
import com.yoidukigembu.passmanagerkt.util.Logger

/**
 * 基底アクティビティ
 */
open class BaseActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.v()
    }

    override fun onStart() {
        super.onStart()
        Logger.v()
    }

    override fun onRestart() {
        super.onRestart()
        Logger.v()
    }

    override fun onResume() {
        super.onResume()
        Logger.v()
    }

    override fun onPause() {
        super.onPause()
        Logger.v()
    }

    override fun onStop() {
        super.onStop()
        Logger.v()
    }

    override fun finish() {
        super.finish()
        Logger.v()
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.v()
    }
}