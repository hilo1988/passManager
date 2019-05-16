package com.hiloislay.passmanagerkt.controller.activity

import android.app.Activity
import android.os.Bundle
import timber.log.Timber

/**
 * 基底アクティビティ
 */
abstract class BaseActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("activity:%s", this)
    }

    override fun onStart() {
        super.onStart()
        Timber.v("activity:%s", this)
    }

    override fun onRestart() {
        super.onRestart()
        Timber.v("activity:%s", this)
    }

    override fun onResume() {
        super.onResume()
        Timber.v("activity:%s", this)
    }

    override fun onPause() {
        super.onPause()
        Timber.v("activity:%s", this)
    }

    override fun onStop() {
        super.onStop()
        Timber.v("activity:%s", this)
    }

    override fun finish() {
        super.finish()
        Timber.v("activity:%s", this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.v("activity:%s", this)
    }
}