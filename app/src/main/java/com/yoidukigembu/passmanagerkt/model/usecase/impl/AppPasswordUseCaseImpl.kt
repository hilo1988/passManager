package com.yoidukigembu.passmanagerkt.model.usecase.impl

import android.content.Context

import com.yoidukigembu.passmanagerkt.PMApplication
import com.yoidukigembu.passmanagerkt.model.usecase.AppPasswordUseCase
import com.yoidukigembu.passmanagerkt.util.Logger
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.lang3.RandomStringUtils
import java.io.IOException
import java.nio.charset.Charset

/**
 * アプリパスワードユースケースの実装クラス
 */
class AppPasswordUseCaseImpl : AppPasswordUseCase {

    override fun save(password: String): Boolean {
        try {
            PMApplication.getContext().openFileOutput(PASS_FILE_NAME, Context.MODE_PRIVATE)
                    .writer(Charset.defaultCharset())
                    .write(DigestUtils.sha512Hex(getSalt() + password))
            Logger.d("アプリパスワードの保存に成功しました。")
        } catch (e: IOException) {
            Logger.e(e, "アプリパスワードの保存に失敗しました")
            return false
        }

        return true
    }


    override fun existsAppPassword() = PMApplication.getContext().getFileStreamPath(PASS_FILE_NAME)
            .exists()


    /**
     * ソルトの取得
     */
    private fun getSalt(): String {
        try {
            return PMApplication.getContext().openFileInput(SALT).reader(Charset.defaultCharset()).readText()
        } catch (e: IOException) {
            val salt = RandomStringUtils.randomAlphanumeric(10)
            PMApplication.getContext().openFileOutput(SALT, Context.MODE_PRIVATE)
                    .writer(Charset.defaultCharset())
                    .write(salt)
            return salt
        }
    }

    override fun isSamePassword(password: String): Boolean {
        val inputPass = DigestUtils.sha512Hex(getSalt() + password)
        val savedPass = PMApplication.getContext()
                .openFileInput(PASS_FILE_NAME)
                .reader(Charset.defaultCharset())
                .readText()

        return inputPass == savedPass
    }

    companion object {
        /** アプリパスワードファイル */
        val PASS_FILE_NAME = "PM_file"

        /** ソルト用ファイル */
        val SALT = "salt_file"
    }
}