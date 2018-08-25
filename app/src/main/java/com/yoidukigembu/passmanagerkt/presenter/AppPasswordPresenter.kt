package com.yoidukigembu.passmanagerkt.presenter

/**
 * アプリパスワード登録プロセサ
 */
interface AppPasswordPresenter {

    /**
     * パスワード登録
     */
    fun createPassword(password: String, passwordConf: String)

    interface FragmentProcessor : BasePresenter.BaseFragmentProcessor {

        /**
         * アプリ用パスワードが生成されたときのコールバック
         */
        fun onPasswordCreated()

        /**
         * アプリ用パスワード生成に失敗したときのコールバック
         */
        fun onPasswordCreateFailure()


        /**
         * エラーメッセージの表示
         */
        fun showError(msg: String)
    }
}