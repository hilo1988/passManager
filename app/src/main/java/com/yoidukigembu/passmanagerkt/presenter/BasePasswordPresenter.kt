package com.yoidukigembu.passmanagerkt.presenter

import com.yoidukigembu.passmanagerkt.accessor.PasswordDataAccessor


interface BasePasswordPresenter : BasePresenter {

    /**
     * パスワードの登録・変更
     */
    fun submit()

    fun createPasswordGenerateDialog(type: PasswordType)

    enum class PasswordType {
        FIRST,
        SECOND
    }

    interface BasePasswordFragmentProcessor : BasePresenter.BaseFragmentProcessor, PasswordDataAccessor {


        /**
         * 登録・更新が終わったときの処理
         */
        fun onSaveFinished()

        fun setPassword1(password: String)

        fun setPassword2(password: String)
    }

}