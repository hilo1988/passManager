package com.hiloislay.passmanagerkt.model.holder

import android.annotation.TargetApi
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import timber.log.Timber
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException


/**
 * Created by hilo on 2016/11/06.
 */
@TargetApi(Build.VERSION_CODES.M)
class KeyStoreHolder @Throws(KeyStoreException::class, NoSuchAlgorithmException::class, NoSuchProviderException::class)
private constructor() {

    val keyStore: KeyStore?

    val keyGenerator: KeyGenerator

    var defaultCipher: Cipher? = null
        private set

    var validatedCipher: Cipher? = null
        private set

    init {
        this.keyStore = KeyStore.getInstance(STORE_NAME)
        this.keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, STORE_NAME)
    }


    /**
     * Cipherの作成
     *
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    @Throws(NoSuchAlgorithmException::class, NoSuchPaddingException::class)
    private fun createCipher() {
        val transformation = String.format("%s/%s/%s", KeyProperties.KEY_ALGORITHM_AES,
                KeyProperties.BLOCK_MODE_CBC,
                KeyProperties.ENCRYPTION_PADDING_PKCS7)
        defaultCipher = Cipher.getInstance(transformation)
        validatedCipher = Cipher.getInstance(transformation)
    }


    private fun initCipher(cipher: Cipher, keyName: String): Boolean {
        try {
            keyStore?.load(null)
            val key = keyStore?.getKey(keyName, null)
            cipher.init(Cipher.ENCRYPT_MODE, key)
        } catch (e: KeyPermanentlyInvalidatedException) {
            return false
        } catch (e: KeyStoreException) {
            throw RuntimeException("Failed to selectPassword Cipher", e)
        } catch (e: CertificateException) {
            throw RuntimeException("Failed to selectPassword Cipher", e)
        } catch (e: UnrecoverableKeyException) {
            throw RuntimeException("Failed to selectPassword Cipher", e)
        } catch (e: IOException) {
            throw RuntimeException("Failed to selectPassword Cipher", e)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to selectPassword Cipher", e)
        } catch (e: InvalidKeyException) {
            throw RuntimeException("Failed to selectPassword Cipher", e)
        }

        return true
    }

    companion object {

        private val STORE_NAME = "psKeyStore"

        private var holder: KeyStoreHolder? = null

        /**
         * KeyStoreHolderの初期化を行います。
         */
        fun init() {
            Timber.d("")
            try {
                holder = KeyStoreHolder()
            } catch (e: KeyStoreException) {
                Timber.e("KeyStoreHolderインスタンスの生成に失敗", e)
                return
            } catch (e: NoSuchAlgorithmException) {
                Timber.e("KeyStoreHolderインスタンスの生成に失敗", e)
                return
            } catch (e: NoSuchProviderException) {
                Timber.e("KeyStoreHolderインスタンスの生成に失敗", e)
                return
            }

            try {
                holder!!.createCipher()
            } catch (e: NoSuchAlgorithmException) {
                Timber.e("cipherの生成に失敗", e)
            } catch (e: NoSuchPaddingException) {
                Timber.e("cipherの生成に失敗", e)
            }

        }


        fun get(): KeyStoreHolder? {
            return holder
        }


        fun createKey(keyName: String, invalidatedByBiometricEnrollment: Boolean) {
            if (holder == null || holder!!.keyStore == null) {
                return
            }
            // The enrolling flow for fingerprint. This is where you ask the user to set up fingerprint
            // for your flow. Use of keys is necessary if you need to know if the set of
            // enrolled fingerprints has changed.
            try {
                holder!!.keyStore!!.load(null)
                // Set the alias of the entry in Android KeyStore where the key will appear
                // and the constrains (purposes) in the constructor of the Builder

                val builder = KeyGenParameterSpec.Builder(keyName,
                        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                        // Require the user to authenticate with a fingerprint to authorize every use
                        // of the key
                        .setUserAuthenticationRequired(true)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)

                // This is a workaround to avoid crashes on devices whose API level is < 24
                // because KeyGenParameterSpec.Builder#setInvalidatedByBiometricEnrollment is only
                // visible on API level +24.
                // Ideally there should be a compat library for KeyGenParameterSpec.Builder but
                // which isn't available yet.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    builder.setInvalidatedByBiometricEnrollment(invalidatedByBiometricEnrollment)
                }
                holder!!.keyGenerator.init(builder.build())
                holder!!.keyGenerator.generateKey()
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException(e)
            } catch (e: InvalidAlgorithmParameterException) {
                throw RuntimeException(e)
            } catch (e: CertificateException) {
                throw RuntimeException(e)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }

        }

        fun initDefaultCipher(keyName: String): Boolean {
            if (holder == null) {
                return false
            }
            return holder!!.initCipher(holder!!.defaultCipher!!, keyName)

        }

        fun initValidatedCipher(keyName: String): Boolean {
            return if (holder == null) false
            else
                holder!!.initCipher(holder!!.validatedCipher!!, keyName)
        }
    }
}
