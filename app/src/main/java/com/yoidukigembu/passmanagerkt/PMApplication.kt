package com.yoidukigembu.passmanagerkt

import android.app.Application
import android.content.Context
import com.yoidukigembu.passmanagerkt.db.entity.OrmaDatabase
import com.yoidukigembu.passmanagerkt.db.repository.impl.BaseRepositoryImpl

class PMApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        self = this


        BaseRepositoryImpl.database = OrmaDatabase
                .builder(this)
                .name("my.db")
                .build()


    }

    companion object {
        /** コンテキスト */
        private var self: PMApplication? = null

        /**
         * コンテキストの取得
         */
        fun getContext(): Context = self!!

    }
}