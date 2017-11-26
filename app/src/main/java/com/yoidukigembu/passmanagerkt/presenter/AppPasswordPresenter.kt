package com.yoidukigembu.passmanagerkt.presenter

import android.content.Context

/**
 * アプリパスワード登録プロセサ
 */
interface AppPasswordPresenter {

    /**
     * パスワード登録
     */
    fun createPassword(password:String, passwordConf:String)

    interface FragmentProcessor {

        /**
         * アプリ用パスワードが生成されたときのコールバック
         */
        fun onPasswordCreated()

        /**
         * アプリ用パスワード生成に失敗したときのコールバック
         */
        fun onPasswordCreateFailure()

        /**
         * コンテキスト取得
         */
        fun getContext(): Context

        /**
         * エラーメッセージの表示
         */
        fun showError(msg : String)
    }
}