package com.hiloislay.passmanagerkt

import android.app.Application
import android.content.Context
import com.yoidukigembu.crypt.aes.AesCryptor
import io.realm.Realm
import io.realm.RealmConfiguration


class PMApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        com.hiloislay.passmanagerkt.PMApplication.Companion.self = this


//        BaseRepositoryImpl.database = OrmaDatabase
//                .builder(this)
//                .name("my.db")
//                .build()
//
//        BaseRepositoryImpl.database.relationOfPassword().createQueryObservable<Password_Selector>()
//                .subscribe { SubjectHolder.onPasswordChangedSubject.onNext(it) }

        initRealm()

    }


    private fun initRealm() {
        Realm.init(this)

        val config = RealmConfiguration.Builder()
                .name("pmkt.realm")
//                .encryptionKey(getRealmKey())
                .schemaVersion(1)
                .build()

        Realm.setDefaultConfiguration(config)

    }


    private fun getRealmKey(): ByteArray {
        val name = "rmk.dat"

        val file = getFileStreamPath(name)

        if (!file.exists()) {
            val keyByte = AesCryptor.generateRandomBytes(128)
            file.writeBytes(keyByte);
            return keyByte
        }

        return file.readBytes()
    }

    companion object {
        /** コンテキスト */
        private var self: com.hiloislay.passmanagerkt.PMApplication? = null

        /**
         * コンテキストの取得
         */
        fun getContext(): Context = com.hiloislay.passmanagerkt.PMApplication.Companion.self!!

    }
}