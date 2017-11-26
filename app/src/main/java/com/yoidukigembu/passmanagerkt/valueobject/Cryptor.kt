package com.yoidukigembu.passmanagerkt.valueobject

import android.content.Context
import com.yoidukigembu.crypt.aes.CbcCryptor
import com.yoidukigembu.crypt.valueobject.CryptData
import com.yoidukigembu.passmanagerkt.PMApplication


/**
 * 暗号化を行うオブジェクト
 */
class Cryptor private constructor(cryptData: CryptData){

    /** CBC暗号化オブジェクト */
    private val cryptor = CbcCryptor(cryptData)

    /**
     * 文字列をバイト配列に暗号化
     */
    fun encrypt(str: String) : ByteArray = cryptor.encrypt(str)

    /**
     * バイト配列を文字列に復元
     */
    fun decrypt(data: ByteArray) : String = cryptor.decryptString(data)


    companion object {
        private val IV = "c_iv.data"
        private val KEY = "c_key.data"
        fun getInstance() : Cryptor {
            val ivFile = PMApplication.getContext().getFileStreamPath(IV)
            if (ivFile?.exists() != true) {
                val data = CryptData.newInstance(128)
                PMApplication.getContext().openFileOutput(IV, Context.MODE_PRIVATE)?.write(data.iv)
                PMApplication.getContext().openFileOutput(KEY, Context.MODE_PRIVATE)?.write(data.key)

                return Cryptor(data)
            }

            val iv = ivFile.readBytes()
            val key = PMApplication.getContext().openFileInput(KEY)?.readBytes()

            return Cryptor(CryptData(key, iv))
        }
    }
}