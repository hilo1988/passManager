package com.hiloislay.passmanagerkt.model.usecase.impl

import android.content.Context
import com.hiloislay.passmanagerkt.PMApplication
import com.hiloislay.passmanagerkt.model.usecase.AppPasswordUseCase
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.lang3.RandomStringUtils
import timber.log.Timber
import java.io.IOException
import java.nio.charset.Charset

/**
 * アプリパスワードユースケースの実装クラス
 */
class AppPasswordUseCaseImpl : AppPasswordUseCase {

    /**
     * アプリパスワードの保存/変更
     * @param password アプリパスワード
     */
    override fun save(password: String): Boolean {
        try {
            val salt = salt(password)
            val writer = PMApplication.getContext().openFileOutput(PASS_FILE_NAME, Context.MODE_PRIVATE)
                    .writer(Charset.defaultCharset())
            writer.write(salt)
            writer.flush()
            Timber.d("アプリパスワードの保存に成功しました。")
        } catch (e: IOException) {
            Timber.e(e, "アプリパスワードの保存に失敗しました")
            return false
        }

        return true
    }


    override fun existsAppPassword() = PMApplication.getContext().getFileStreamPath(PASS_FILE_NAME)
            .exists()


    /**
     * パスワードをソルト化
     * @param str ソルトする文字列
     * @return ソルト化した文字列
     */
    private fun salt(str: String) = String(Hex.encodeHex(DigestUtils.sha512(getSalt() + str)))

    /**
     * ソルトの取得
     */
    private fun getSalt(): String {
        try {
            return PMApplication.getContext().openFileInput(SALT).reader(Charset.defaultCharset()).readText()
        } catch (e: IOException) {
            val salt = RandomStringUtils.randomAlphanumeric(10)
            val writer = PMApplication.getContext().openFileOutput(SALT, Context.MODE_PRIVATE)
                    .writer(Charset.defaultCharset())
            writer.write(salt)
            writer.flush()
            return salt
        }
    }

    override fun isSamePassword(password: String): Boolean {
        val inputPass = salt(password)
        val savedPass = PMApplication.getContext()
                .openFileInput(PASS_FILE_NAME)
                .reader(Charset.defaultCharset())
                .readText()

        Timber.v("input:[%s] saved:[%s]", inputPass, savedPass)

        return inputPass == savedPass
    }

    companion object {
        /** アプリパスワードファイル */
        val PASS_FILE_NAME = "PM_file"

        /** ソルト用ファイル */
        val SALT = "salt_file"
    }
}