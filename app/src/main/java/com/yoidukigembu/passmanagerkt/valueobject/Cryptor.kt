package com.yoidukigembu.passmanagerkt.valueobject

import android.content.Context
import com.yoidukigembu.crypt.aes.CbcCryptor
import com.yoidukigembu.passmanagerkt.PMApplication
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


/**
 * 暗号化を行うオブジェクト
 */
class Cryptor private constructor(private val cryptor: CbcCryptor) {


    /**
     * 文字列をバイト配列に暗号化
     */
    fun encrypt(str: String): ByteArray = cryptor.encrypt(str)

    /**
     * バイト配列を文字列に復元
     */
    fun decrypt(data: ByteArray): String = cryptor.decryptString(data)


    companion object {
        private val IV = "c_iv.data"
        private val KEY = "c_key.data"
        fun getInstance(): Cryptor {

            ReentrantLock().withLock {
                val ivFile = PMApplication.getContext().getFileStreamPath(IV)
                if (ivFile?.exists() != true) {
                    val cbc = CbcCryptor(128)
                    cbc.encrypt("asd")
                    PMApplication.getContext().openFileOutput(IV, Context.MODE_PRIVATE)?.write(cbc.iv)
                    PMApplication.getContext().openFileOutput(KEY, Context.MODE_PRIVATE)?.write(cbc.keyBytes)
                    return Cryptor(cbc)
                }

                val iv = ivFile.readBytes()
                val key = PMApplication.getContext().openFileInput(KEY)?.readBytes()

                return Cryptor(CbcCryptor(key, iv))
            }

        }
    }
}