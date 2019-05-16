package com.hiloislay.passmanagerkt

import android.app.Application
import android.content.Context
import com.yoidukigembu.crypt.aes.AesCryptor
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber


class PMApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        self = this


//        BaseRepositoryImpl.database = OrmaDatabase
//                .builder(this)
//                .name("my.db")
//                .build()
//
//        BaseRepositoryImpl.database.relationOfPassword().createQueryObservable<Password_Selector>()
//                .subscribe { SubjectHolder.onPasswordChangedSubject.onNext(it) }

        Timber.plant(Timber.DebugTree())
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

//        SaveMyPass().save()

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
        private var self: PMApplication? = null

        /**
         * コンテキストの取得
         */
        fun getContext(): Context = self!!

    }
}
