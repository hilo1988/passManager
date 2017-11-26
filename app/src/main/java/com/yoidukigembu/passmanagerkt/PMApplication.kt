package com.yoidukigembu.passmanagerkt

import android.app.Application
import android.content.Context

class PMApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        self = this

    }

    companion object {
        /** コンテキスト */
        private var self: Context? = null

        /**
         * コンテキストの取得
         */
        fun getContext(): Context = self!!
    }
}