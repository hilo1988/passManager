package com.yoidukigembu.passmanagerkt.presenter

/**
 * ログインプレゼンタ
 */
interface LoginPresenter {

    /**
     * ログイン
     */
    fun login(password: String)

    /**
     * フラグメント処理のI/F
     */
    interface FragmentProcessor {

        /**
         * ログインに成功した時の処理
         */
        fun onLoginSuccess()

        /**
         * ログインに失敗したときの処理
         */
        fun onLoginFailed()

        /**
         * エラーの表示
         */
        fun showError(error: String)
    }
}